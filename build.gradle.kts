import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.siouan.frontendgradleplugin.infrastructure.gradle.FrontendExtension

group = "jetbrains.kotlin.course"
version = "0.0.1-SNAPSHOT"

fun properties(key: String) = project.findProperty(key).toString()

@Suppress("DSL_SCOPE_VIOLATION") // "libs" produces a false-positive warning, see https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    java
    val kotlinVersion = "2.4.10"
    id("org.jetbrains.kotlin.jvm") version kotlinVersion apply false
    id("org.jetbrains.kotlin.multiplatform") version kotlinVersion apply false
    id("org.springframework.boot") version "3.5.16" apply false
    id("io.spring.dependency-management") version "1.1.7" apply false
    id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion apply false

    id("org.siouan.frontend-jdk17") version "10.0.0" apply false
}

allprojects {
    repositories {
        mavenCentral()
        maven {
            url = uri("https://packages.jetbrains.team/maven/p/kotlin-test-framework/kotlin-test-framework")
        }
    }
}

tasks {
    wrapper {
        gradleVersion = properties("gradleVersion")
    }
}

val frontendSuffix = "Frontend"
val server = "Server"

configure(subprojects.filter { frontendSuffix !in it.name }) {
    apply {
        plugin("java")
        plugin("kotlin")
    }

    dependencies {
        implementation("org.jetbrains.academy.test.system:core:2.0.7")
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    tasks {
        withType<KotlinCompile> {
            compilerOptions {
                // Keep in sync with source/target versions in `java` block above
                freeCompilerArgs.add("-Xjsr305=strict")
                jvmTarget = JvmTarget.JVM_17
            }
        }

        withType<Test> {
            useJUnitPlatform()

            outputs.upToDateWhen { false }

            addTestListener(object : TestListener {
                override fun beforeSuite(suite: TestDescriptor) {}
                override fun beforeTest(testDescriptor: TestDescriptor) {}
                override fun afterTest(testDescriptor: TestDescriptor, result: TestResult) {
                    if (result.resultType == TestResult.ResultType.FAILURE) {
                        val message = result.exception?.message ?: "Wrong answer"
                        val lines = message.split("\n")
                        println("#educational_plugin FAILED + ${lines[0]}")
                        lines.subList(1, lines.size).forEach { line ->
                            println("#educational_plugin$line")
                        }
                        // we need this to separate output of different tests
                        println()
                    }
                }

                override fun afterSuite(suite: TestDescriptor, result: TestResult) {}
            })
        }
    }
}

fun String.getGameName(suffix: String) = substring(0 until indexOf(suffix))

configure(subprojects.filter { server in it.name || "utils" in it.name }) {
    apply {
        plugin("java")
        plugin("kotlin")
    }

    dependencies {
        val junitJupiterVersion = "5.12.2"
        implementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
        testImplementation("org.junit.jupiter:junit-jupiter-params:$junitJupiterVersion")
        testRuntimeOnly("org.junit.platform:junit-platform-console:1.12.2")
    }
}

configure(subprojects.filter { server in it.name }) {
    val projectName = this.name
    val gameName = projectName.getGameName(server)

    apply {
        plugin("java")
        plugin("kotlin")
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
        plugin("org.jetbrains.kotlin.plugin.spring")
    }

    dependencies {
        implementation(project(":utils"))

        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    }

//    tasks.named("processResources") {
//        dependsOn(":$gameName$frontendSuffix:build")
//    }

    sourceSets {
        getByName("main").java.srcDirs("$gameName/src/main/kotlin")
        getByName("main").resources.srcDirs("$gameName/src/main/resources")
        getByName("test").java.srcDirs("test")
    }

    tasks.register<Exec>("run") {
        // Just do nothing to avoid the edu plugin errors
    }
}

configure(subprojects.filter { frontendSuffix in it.name }) {
    val projectName = this.name
    val gameName = projectName.getGameName(frontendSuffix)

    apply {
        plugin("org.siouan.frontend-jdk17")
    }

    configure<FrontendExtension> {
        nodeDistributionProvided.set(false)
        nodeVersion.set("16.17.1")

        // Yarn Berry enables immutable installs whenever CI=true, which forbids creating a lockfile.
        // `yarn.lock` is not committed here (see the frontend .gitignore files), so the install must
        // be allowed to write it. Drop this flag if lockfiles are ever committed.
        installScript.set("install --no-immutable")
    }

    val yarnRunBuildTask = tasks.register<Exec>("yarnRunBuildTask") {
        commandLine("yarn", "run", "build")
    }

    val serveResourcesTask = tasks.register("serveResources") {
        dependsOn(yarnRunBuildTask)
        val serverResources = rootProject.subprojects
            .filter { gameName in it.name && server in it.name }
            // the project name looks like: gameName-moduleName
            .map { "$rootDir/$gameName$server/${it.name.split('-').last()}/src/main/resources/static/" }
        val staticFolder = "$rootDir/$gameName$frontendSuffix/build"
        doLast {
            serverResources.forEach { resourcesPath ->
                rootProject.delete(resourcesPath)
                copy {
                    from(staticFolder)
                    into(resourcesPath)
                }
            }
        }
    }

    serveResourcesTask {
        mustRunAfter(":$gameName$frontendSuffix:${yarnRunBuildTask.name}")
    }
}
