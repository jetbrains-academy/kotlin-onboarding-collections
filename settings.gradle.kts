rootProject.name = "CSC_Kotlin_Course_Part3"

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven(url = "https://packages.jetbrains.team/maven/p/kotlin-test-framework/kotlin-test-framework")
    }
}

rootProject.projectDir.walkTopDown().forEach {
    if (!isTaskDir(it) || it.path.contains(".idea") || it.path.contains("build") || it.path.contains("node_modules")) {
        return@forEach
    }
    val taskRelativePath = rootDir.toPath().relativize(it.toPath())
    val parts = mutableListOf<String>()
    for (name in taskRelativePath) {
        parts.add(sanitizeName(name.toString()))
    }
    val moduleName = parts.joinToString("-")
    include(moduleName)
    project(":$moduleName").projectDir = it
}

fun sanitizeName(name: String) =
    name.replace("listOf( /\\\\:<>\"?*|())", "_").replace("(^listOf(.)+)|(listOf(.)+\$)", "")

fun isTaskDir(dir: File) = File(dir, "src").exists()

include(
    "utils",
)
