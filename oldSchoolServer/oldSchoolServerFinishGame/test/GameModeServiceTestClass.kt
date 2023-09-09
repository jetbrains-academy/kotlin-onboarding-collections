import org.jetbrains.academy.test.system.core.models.TestKotlinType
import org.jetbrains.academy.test.system.core.models.Visibility
import org.jetbrains.academy.test.system.core.models.classes.TestClass
import org.jetbrains.academy.test.system.core.models.method.TestMethod
import org.jetbrains.academy.test.system.core.models.variable.TestVariable
import org.jetbrains.academy.test.system.core.models.variable.VariableMutability


internal val generateMapOfCharactersMethod = TestMethod(
    "generateMapOfCharacters",
    TestKotlinType(
        "java.util.Map",
        params = listOf(photoCharacterTestClass.getFullName(), "java.lang.String"),
    ),
    returnTypeJava = "Map"
)

internal val generateSetOfCharactersMethod = TestMethod(
    "generateSetOfCharacters",
    TestKotlinType(
        "Set",
        abbreviation = photoCharacterTestClass.getFullName(),
    ),
)

internal val generateRandomCharacterMethod = TestMethod(
    "generateRandomCharacter",
    TestKotlinType(
        photoCharacterTestClass.getFullName(),
    ),
    returnTypeJava = photoCharacterTestClass.name,
    visibility = Visibility.PRIVATE
)

internal val generateListOfCharactersMethod = TestMethod(
    "generateListOfCharacters",
    TestKotlinType(
        "List",
        abbreviation = photoCharacterTestClass.getFullName(),
    ),
)

internal val maxNumberOfPhotosVariable = TestVariable(
    name = "MAX_NUMBER_OF_PHOTOS",
    javaType = "int",
    // Because it is inside companion object
    visibility = Visibility.PRIVATE,
    mutability = VariableMutability.VAL,
    isStatic = true,
    isConst = true
)

internal val gameModeServiceTestClass = TestClass(
    "GameModeService",
    "org.jetbrains.kotlin.course.old.school.mode",
    declaredFields = listOf(
        maxNumberOfPhotosVariable
    ),
    customMethods = listOf(
        generateRandomCharacterMethod,
        generateListOfCharactersMethod,
        generateSetOfCharactersMethod,
        generateMapOfCharactersMethod
    ),
)

internal val gameModeServiceCompanionTestClass = TestClass(
    "GameModeService\$Companion",
    "org.jetbrains.kotlin.course.old.school.mode",
)
