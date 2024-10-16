import org.jetbrains.academy.test.system.core.models.TestKotlinType
import org.jetbrains.academy.test.system.core.models.classes.TestClass
import org.jetbrains.academy.test.system.core.models.method.TestMethod
import org.jetbrains.academy.test.system.core.models.variable.TestVariable

internal val getFruitsForSmoothieMethod = TestMethod(
    "getFruitsForSmoothie",
    TestKotlinType("List", params = listOf("org.jetbrains.kotlin.course.culinary.ingredient.Fruit")),
)

internal val cookSmoothieMethod = TestMethod(
    "cookSmoothie",
    TestKotlinType("Unit"),
    arguments = listOf(
        TestVariable(
            name = "fruit",
            javaType = "List",
            kotlinType = TestKotlinType("List", params = listOf("org.jetbrains.kotlin.course.culinary.ingredient.Fruit")),
        ),
    ),
    returnTypeJava = "void",
)

internal val smoothieKtTestClass = TestClass(
    "SmoothieKt",
    "org.jetbrains.kotlin.course.culinary.game.recipes",
    customMethods = listOf(
        getFruitsForSmoothieMethod,
        cookSmoothieMethod
    ),
)
