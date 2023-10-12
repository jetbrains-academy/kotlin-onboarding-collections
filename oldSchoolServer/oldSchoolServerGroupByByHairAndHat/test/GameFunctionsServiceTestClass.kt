import org.jetbrains.academy.test.system.core.models.TestKotlinType
import org.jetbrains.academy.test.system.core.models.Visibility
import org.jetbrains.academy.test.system.core.models.classes.TestClass
import org.jetbrains.academy.test.system.core.models.method.TestMethod
import org.jetbrains.academy.test.system.core.models.variable.TestVariable


internal val groupPhotosByHairAndHatMethod = TestMethod(
    "groupPhotosByHairAndHat",
    TestKotlinType(
        "List",
        abbreviation = photoCharacterTestClass.getFullName()
    ),
    arguments = listOf(
        TestVariable(
            name = "this",
            javaType = "Iterable",
            kotlinType = TestKotlinType(
                "Iterable",
                abbreviation = "String"
            )
        ),
    ),
)

internal val groupPhotosByColorMethod = TestMethod(
    "groupPhotosByColor",
    TestKotlinType(
        "List",
        abbreviation = photoCharacterTestClass.getFullName()
    ),
    arguments = listOf(
        TestVariable(
            name = "this",
            javaType = "Iterable",
            kotlinType = TestKotlinType(
                "Iterable",
                abbreviation = "String"
            )
        ),
    ),
)

internal val findPhotoMethod = TestMethod(
    "findPhoto",
    TestKotlinType(
        "PhotoCharacter",
        isNullable = true
    ),
    arguments = listOf(
        TestVariable(
            name = "this",
            javaType = "Iterable",
            kotlinType = TestKotlinType(
                "Iterable",
                abbreviation = "String"
            )
        ),
        TestVariable(
            name = "colorStr",
            javaType = "String",
        ),
    ),
)

internal val toPhotoCharactersMethod = TestMethod(
    "toPhotoCharacters",
    TestKotlinType(
        "List",
        abbreviation = photoCharacterTestClass.getFullName(),
    ),
    arguments = listOf(
        TestVariable(
            name = "this",
            javaType = "Iterable",
            kotlinType = TestKotlinType(
                "Iterable",
                abbreviation = "String"
            )
        ),
    ),
    visibility = Visibility.PRIVATE,
)

internal val toColorMethod = TestMethod(
    "toColor",
    TestKotlinType(
        "Color"
    ),
    arguments = listOf(
        TestVariable(
            name = "this",
            javaType = "String",
        ),
    ),
    visibility = Visibility.PRIVATE,
)

internal val getAllPossibleColorsMethod = TestMethod(
    "getAllPossibleColors",
    TestKotlinType(
        "List",
        abbreviation = "kotlin.String",
    ),
)

internal val gameFunctionsServiceTestClass = TestClass(
    "GameFunctionsService",
    "org.jetbrains.kotlin.course.old.school.functions",
    customMethods = listOf(
        getAllPossibleColorsMethod,
        toColorMethod,
        toPhotoCharactersMethod,
        findPhotoMethod,
        groupPhotosByColorMethod,
        groupPhotosByHairAndHatMethod,
    ),
)
