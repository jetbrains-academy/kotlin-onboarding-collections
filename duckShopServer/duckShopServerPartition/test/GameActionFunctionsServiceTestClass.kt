import org.jetbrains.academy.test.system.core.models.TestKotlinType
import org.jetbrains.academy.test.system.core.models.classes.TestClass
import org.jetbrains.academy.test.system.core.models.method.TestMethod
import org.jetbrains.academy.test.system.core.models.variable.TestVariable

internal val deleteDucksWithoutKotlinStuffCollectionMethod = TestMethod(
    "deleteDucksWithoutKotlinStuff",
    returnType = TestKotlinType(
        "Collection",
        params = listOf("org.jetbrains.kotlin.course.duck.shop.duck.Duck"),
        possibleBounds = listOf("List")
    ),
    arguments = listOf(
        TestVariable(
            "collection",
            javaType = "Collection",
            kotlinType = TestKotlinType(
                "Collection",
                params = listOf("org.jetbrains.kotlin.course.duck.shop.duck.Duck"),
            ),
        )
    )
)

internal val deleteDucksWithoutKotlinStuffMapMethod = TestMethod(
    "deleteDucksWithoutKotlinStuff",
    returnType = TestKotlinType(
        "Collection",
        params = listOf("org.jetbrains.kotlin.course.duck.shop.duck.Duck", "kotlin.String"),
        possibleBounds = listOf("Map")
    ),
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

internal val divideDucksIntoKotlinAndNonKotlinMethod = TestMethod(
    "divideDucksIntoKotlinAndNonKotlin",
    returnType = TestKotlinType(
        "Pair",
        params = listOf("kotlin.collections.Collection"),
    ),
    returnTypeJava = "Pair",
    arguments = listOf(
        TestVariable(
            "collection",
            javaType = "Collection",
            kotlinType = TestKotlinType(
                "Collection",
                params = listOf("org.jetbrains.kotlin.course.duck.shop.duck.Duck"),
            ),
        )
    )
)

internal val gameActionFunctionsServiceTestClass = TestClass(
    "GameActionFunctionsService",
    "org.jetbrains.kotlin.course.duck.shop.functions.action",
    customMethods = listOf(
        deleteDucksWithoutKotlinStuffCollectionMethod,
        deleteDucksWithoutKotlinStuffMapMethod,

        divideDucksIntoKotlinAndNonKotlinMethod
    ),
)
