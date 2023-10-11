plugins {
    val kotlinVersion = "1.9.0"
    kotlin("multiplatform") version kotlinVersion
}

group = rootProject.group
version = rootProject.version

kotlin {
    js(IR) {
        binaries.executable()
        browser {
        }
        generateTypeScriptDefinitions()
    }
    jvm()
}

tasks {
    "build" {
        doLast {
            copy {
                from("$buildDir/processedResources/js/main")
                into("$buildDir/libs/common-types")
            }
            copy {
                from("$buildDir/compileSync/js/main/productionExecutable/kotlin")
                into("$buildDir/libs/common-types")
                rename { name -> name.replace("${rootProject.name}-common", "index") }
            }
        }
    }
}
