import org.jetbrains.academy.test.system.core.models.TestKotlinType
import org.jetbrains.academy.test.system.core.models.classes.TestClass
import org.jetbrains.academy.test.system.core.models.method.TestMethod
import org.jetbrains.academy.test.system.core.models.variable.TestVariable

internal val removeRandomDuckListMethod = TestMethod(
    "removeRandomDuck",
    returnType = TestKotlinType(
        "List",
        params = listOf("org.jetbrains.kotlin.course.duck.shop.duck.Duck"),
    ),
    returnTypeJava = "List",
    arguments = listOf(
        TestVariable(
            "list",
            javaType = "List",
            kotlinType = TestKotlinType(
                "List",
                params = listOf("org.jetbrains.kotlin.course.duck.shop.duck.Duck"),
            ),
        )
    )
)

internal val removeRandomDuckSetMethod = TestMethod(
    "removeRandomDuck",
    returnType = TestKotlinType(
        "Set",
        params = listOf("org.jetbrains.kotlin.course.duck.shop.duck.Duck"),
    ),
    returnTypeJava = "Set",
    arguments = listOf(
        TestVariable(
            "set",
            javaType = "Set",
            kotlinType = TestKotlinType(
                "Set",
                params = listOf("org.jetbrains.kotlin.course.duck.shop.duck.Duck"),
            ),
        )
    )
)

internal val removeRandomDuckMapMethod = TestMethod(
    "removeRandomDuck",
    returnType = TestKotlinType(
        "Map",
        params = listOf("org.jetbrains.kotlin.course.duck.shop.duck.Duck", "kotlin.String"),
    ),
    returnTypeJava = "Map",
    arguments = listOf(
        TestVariable(
            "map",
            javaType = "Map",
            kotlinType = TestKotlinType(
                "Map",
                params = listOf("org.jetbrains.kotlin.course.duck.shop.duck.Duck", "kotlin.String"),
            ),
        )
    )
)

internal val gameChangeFunctionsServiceTestClass = TestClass(
    "GameChangeFunctionsService",
    "org.jetbrains.kotlin.course.duck.shop.functions.change",
    customMethods = listOf(
        removeRandomDuckListMethod,
        removeRandomDuckSetMethod,
        removeRandomDuckMapMethod
    ),
)
