import org.jetbrains.academy.test.system.core.models.TestKotlinType
import org.jetbrains.academy.test.system.core.models.Visibility
import org.jetbrains.academy.test.system.core.models.classes.ClassType
import org.jetbrains.academy.test.system.core.models.classes.TestClass
import org.jetbrains.academy.test.system.core.models.method.TestMethod

internal val generateRandomVegetablesMethod = TestMethod(
    "generateRandomVegetables",
    TestKotlinType("List", params = listOf("org.jetbrains.kotlin.course.culinary.ingredient.Vegetable")),
    visibility = Visibility.PRIVATE,
)

internal val refillMethod = TestMethod(
    "refill",
    TestKotlinType("Unit"),
    returnTypeJava = "void",
)

internal val fridgeImplTestClass = TestClass(
    "FridgeImpl",
    "org.jetbrains.kotlin.course.culinary.implementation.storage",
    classType = ClassType.OBJECT,
    customMethods = listOf(
        generateRandomVegetablesMethod,
        refillMethod
    ),
)
