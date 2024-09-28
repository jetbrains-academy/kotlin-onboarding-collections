import org.jetbrains.academy.test.system.core.models.TestKotlinType
import org.jetbrains.academy.test.system.core.models.classes.TestClass
import org.jetbrains.academy.test.system.core.models.method.TestMethod
import org.jetbrains.academy.test.system.core.models.variable.TestVariable

internal val getTomatoesForSoupMethod = TestMethod(
    "getTomatoesForSoup",
    TestKotlinType("List", params = listOf("org.jetbrains.kotlin.course.culinary.ingredient.Vegetable")),
)

internal val prepareTomatoesMethod = TestMethod(
    "prepareTomatoes",
    TestKotlinType("Unit"),
    returnTypeJava = "void",
    arguments = listOf(
        TestVariable(
            name = "tomatoes",
            javaType = "List",
            kotlinType = TestKotlinType("List", params = listOf("org.jetbrains.kotlin.course.culinary.ingredient.Vegetable")),
        ),
    )
)

internal val tomatoSoupKtTestClass = TestClass(
    "TomatoSoupKt",
    "org.jetbrains.kotlin.course.culinary.game.recipes",
    customMethods = listOf(
        getTomatoesForSoupMethod,
        prepareTomatoesMethod
    ),
)