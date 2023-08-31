import org.jetbrains.academy.test.system.core.models.TestKotlinType
import org.jetbrains.academy.test.system.core.models.classes.TestClass
import org.jetbrains.academy.test.system.core.models.method.TestMethod

internal val generateListOfDucksMethod = TestMethod(
    "generateListOfDucks",
    returnType = TestKotlinType(
        "List",
        params = listOf("org.jetbrains.kotlin.course.duck.shop.duck.Duck"),
    ),
    returnTypeJava = "List",
)

internal val gameModeServiceTestClass = TestClass(
    "GameModeService",
    "org.jetbrains.kotlin.course.duck.shop.mode",
    customMethods = listOf(
        generateListOfDucksMethod
    ),
)
