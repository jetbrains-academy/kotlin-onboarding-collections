import org.jetbrains.academy.test.system.core.models.TestKotlinType
import org.jetbrains.academy.test.system.core.models.classes.TestClass
import org.jetbrains.academy.test.system.core.models.method.TestMethod

internal val generateRandomDuckMethod = TestMethod(
    "generateRandomDuck",
    TestKotlinType("Duck"),
)

internal val duckClass = TestClass(
    name = "DuckKt",
    classPackage = "org.jetbrains.kotlin.course.duck.shop.duck",
    customMethods = listOf(
        generateRandomDuckMethod,
    )
)
