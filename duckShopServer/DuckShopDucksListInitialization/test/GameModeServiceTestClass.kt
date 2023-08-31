import org.jetbrains.academy.test.system.core.models.TestKotlinType
import org.jetbrains.academy.test.system.core.models.classes.TestClass
import org.jetbrains.academy.test.system.core.models.method.TestMethod

internal val generateListOfDucksMethod = TestMethod(
    "generateListOfDucks",
    returnType = TestKotlinType(
        "List",
        abbreviation = "jetbrains.kotlin.course.alias.card.Card"
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
