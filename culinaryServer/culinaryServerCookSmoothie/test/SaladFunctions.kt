import org.jetbrains.academy.test.system.core.models.TestKotlinType
import org.jetbrains.academy.test.system.core.models.classes.TestClass
import org.jetbrains.academy.test.system.core.models.method.TestMethod
import org.jetbrains.academy.test.system.core.models.variable.TestVariable

internal val getFreshVegetablesMethod = TestMethod(
    "getFreshVegetables",
    TestKotlinType("List", params = listOf("org.jetbrains.kotlin.course.culinary.ingredient.Vegetable")),
)

internal val cutMethod = TestMethod(
    "cut",
    TestKotlinType("List", params = listOf("org.jetbrains.kotlin.course.culinary.ingredient.CutVegetable")),
    arguments = listOf(
        TestVariable(
            name = "vegetables",
            javaType = "List",
            kotlinType = TestKotlinType("List", params = listOf("org.jetbrains.kotlin.course.culinary.ingredient.Vegetable")),
        ),
    )
)

internal val mixVegetablesForSaladMethod = TestMethod(
    "mixVegetablesForSalad",
    TestKotlinType("Unit"),
    arguments = listOf(
        TestVariable(
            name = "cutVegetables",
            javaType = "List",
            kotlinType = TestKotlinType("List", params = listOf("org.jetbrains.kotlin.course.culinary.ingredient.CutVegetable")),
        ),
    ),
    returnTypeJava = "void",
)

internal val saladKtTestClass = TestClass(
    "SaladKt",
    "org.jetbrains.kotlin.course.culinary.game.recipes",
    customMethods = listOf(
        getFreshVegetablesMethod,
        cutMethod,
        mixVegetablesForSaladMethod
    ),
)
