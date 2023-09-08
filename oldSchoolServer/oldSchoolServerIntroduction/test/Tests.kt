import org.jetbrains.academy.test.system.core.models.classes.ConstructorGetter
import org.jetbrains.academy.test.system.core.models.method.TestMethod
import org.jetbrains.academy.test.system.core.models.method.TestMethodInvokeData
import org.jetbrains.kotlin.course.old.school.photo.Accessory
import org.jetbrains.kotlin.course.old.school.photo.PhotoCharacter
import org.junit.jupiter.api.Test
import java.lang.reflect.InvocationTargetException
import java.util.*

class Test {
    companion object {
        private enum class CollectionType {
            List,
            Set,
            Map
            ;
        }

        private val possibleExpectedColors =
            listOf("white", "blue", "orange", "yellow", "pink", "purple", "green", "gray")
        private val possibleCharactersExpected = PhotoCharacter.values().map { it.name }
        private val possibleCharactersArgs = possibleCharactersExpected.map { it.lowercase() }
    }

    @Test
    fun groupPhotosByHairAndHatMethodTest() {
        testGroupByMethods(groupPhotosByHairAndHatMethod) { currentCharacters ->
            currentCharacters.groupBy { it.hairType }
                .mapValues { it.value.groupBy { character ->
                    character.accessories?.contains(Accessory.Hat) ?: false
                }
                }.values.flatMap { it.values }
                .flatten()
        }
    }

    @Test
    fun groupPhotosByColorMethodTest() {
        testGroupByMethods(groupPhotosByColorMethod) { currentCharacters ->
            currentCharacters.groupBy { it.backgroundColor }.map { it.value }.flatten()
        }
    }

    private fun testGroupByMethods(
        testMethod: TestMethod,
        expectedResultFunction: (List<PhotoCharacter>) -> List<PhotoCharacter>
    ) {
        val invokeData = TestMethodInvokeData(gameFunctionsServiceTestClass, testMethod)
        repeat(100) {
            val currentCharacters = PhotoCharacter.values().toList().shuffled().take(12)
            val currentInput = currentCharacters.map { it.name.lowercase() }
            // TODO: replace with input/output data to hide the implementation??
            val expectedResult = expectedResultFunction(currentCharacters)
            val actualResult = try {
                gameFunctionsServiceTestClass.invokeMethodWithArgs(
                    args = arrayOf(currentInput),
                    invokeData = invokeData,
                ).toString()
            } catch (e: InvocationTargetException) {
                assert(false) { "Try to invoke function ${testMethod.name} with argument $currentInput, but got an unexpected error!" }
            }
            assert(expectedResult.toString() == actualResult) { "The method ${testMethod.name} with argument $currentInput should return $expectedResult, but the current implementation returns $actualResult" }
        }
    }

    @Test
    fun toColorMethodTest() {
        val invokeData = TestMethodInvokeData(gameFunctionsServiceTestClass, toColorMethod)
        possibleExpectedColors.forEach { expectedColor ->
            val actualColor = try {
                gameFunctionsServiceTestClass.invokeMethodWithArgs(
                    args = arrayOf(expectedColor),
                    invokeData = invokeData,
                    isPrivate = true
                ).toString()
            } catch (e: InvocationTargetException) {
                assert(false) { "Try to invoke function ${toColorMethod.name} with argument $expectedColor, but got an unexpected error!" }
            }
            val expectedOutput = expectedColor.replaceFirstChar { it.titlecase(Locale.getDefault()) }
            assert(expectedOutput == actualColor) { "The function ${toColorMethod.name} should return $expectedOutput for input $expectedColor." }
        }
    }

    @Test
    fun toPhotoCharactersMethodTest() {
        val invokeData = TestMethodInvokeData(gameFunctionsServiceTestClass, toPhotoCharactersMethod)
        val possibleCharactersActual = try {
            gameFunctionsServiceTestClass.invokeMethodWithArgs(
                args = arrayOf(possibleCharactersArgs),
                invokeData = invokeData,
                isPrivate = true
            ).toString()
        } catch (e: InvocationTargetException) {
            assert(false) { "Try to invoke function ${toPhotoCharactersMethod.name} with argument $toPhotoCharactersMethod, but got an unexpected error!" }
        }
        assert(possibleCharactersExpected.toString() == possibleCharactersActual) { "The method ${toPhotoCharactersMethod.name} for the list of names $possibleCharactersArgs should return $possibleCharactersExpected, but the current implementation returns $possibleCharactersActual" }
    }

    // TODO: add tests with null background
    @Test
    fun findPhotoMethodBaseTest() {
        repeat(100) {
            val invokeData = TestMethodInvokeData(gameFunctionsServiceTestClass, findPhotoMethod)
            val characters = PhotoCharacter.values()
            characters.shuffle()
            characters.forEach { photoCharacter ->
                val background = photoCharacter.backgroundColor.name
                val photoCharacterActual = invokeData.callFindPhotoMethod(characters.toList(), background)
                val expectedPhotoCharacter =
                    characters.find { it.backgroundColor.toString().lowercase() == background.lowercase() }
                assert(expectedPhotoCharacter!!.name == photoCharacterActual) { "The method ${findPhotoMethod.name} with arguments: $possibleCharactersArgs, and $background should return ${photoCharacter.name}, but the current implementation returns $photoCharacterActual." }
            }
        }
    }

    private fun TestMethodInvokeData.callFindPhotoMethod(
        characters: List<PhotoCharacter>,
        background: String
    ): String? {
        return try {
            gameFunctionsServiceTestClass.invokeMethodWithArgs(
                args = arrayOf(characters.map { it.name }, background),
                invokeData = this,
            ).toString()
        } catch (e: InvocationTargetException) {
            assert(false) { "Try to invoke function ${findPhotoMethod.name} with arguments: $possibleCharactersArgs, and $background but got an unexpected error!" }
            null
        }
    }

    @Test
    fun gameFunctionsServiceTestClassTest() {
        val clazz = gameFunctionsServiceTestClass.checkBaseDefinition()
        gameFunctionsServiceTestClass.checkDeclaredMethods(clazz)
        gameFunctionsServiceTestClass.checkConstructors(
            clazz,
            listOf(
                ConstructorGetter(),
            )
        )
    }

    @Test
    fun getAllPossibleColorsMethodTest() {
        val invokeData = TestMethodInvokeData(gameFunctionsServiceTestClass, getAllPossibleColorsMethod)
        val colors = gameFunctionsServiceTestClass.invokeMethodWithoutArgs(
            invokeData = invokeData,
        ).toString()
        val colorsParsed = colors.removePrefix("[").removeSuffix("]").split(",").map { it.trim() }
        assert(colorsParsed.size == possibleExpectedColors.size) { "The method ${getAllPossibleColorsMethod.name} should return ${possibleExpectedColors.size} different colors!" }
        possibleExpectedColors.forEach { expectedColor ->
            assert(expectedColor in colorsParsed) { "The method ${getAllPossibleColorsMethod.name} should return ${possibleExpectedColors.size} different colors, including $expectedColor!" }
        }
    }

    @Test
    fun generateMapOfCharactersMethodTest() {
        testCollectionGeneration(generateMapOfCharactersMethod, CollectionType.Map) { characters ->
            val charactersMap = characters.drop(1).dropLast(1).split(",")
            assert(charactersMap.toSet().size == charactersMap.size) { "The method ${generateMapOfCharactersMethod.name} should generate a set of characters - all characters in the generated collection must be different." }
        }
    }

    @Test
    fun generateSetOfCharactersMethodTest() {
        testCollectionGeneration(generateSetOfCharactersMethod, CollectionType.Set) { characters ->
            val charactersSet = characters.drop(1).dropLast(1).split(",")
            assert(charactersSet.toSet().size == charactersSet.size) { "The method ${generateSetOfCharactersMethod.name} should generate a set of characters - all characters in the generated collection must be different." }
        }
    }

    @Test
    fun gameModeServiceTestClassTest() {
        val clazz = gameModeServiceTestClass.checkBaseDefinition()
        gameModeServiceCompanionTestClass.checkBaseDefinition()
        gameModeServiceTestClass.checkDeclaredMethods(clazz)
        val constructor = gameModeServiceTestClass.checkConstructors(
            clazz,
            listOf(
                ConstructorGetter(),
            )
        )

        // Check MAX_NUMBER_OF_PHOTOS values
        val instance = constructor.newInstance()
        val field = clazz.declaredFields.find { it.name == maxNumberOfPhotosVariable.name }
            ?: error("Can not find the field ${maxNumberOfPhotosVariable.name}")
        field.isAccessible = true
        val maxNumberOfPhotosValue = field.get(instance)
        assert(12 == maxNumberOfPhotosValue as Int) { "The value of the field ${maxNumberOfPhotosVariable.name} must be 12." }
    }

    @Test
    fun generateRandomCharacterMethodTest() {
        val generatedCharacters = mutableSetOf<String>()
        repeat(100) {
            val invokeData = TestMethodInvokeData(gameModeServiceTestClass, generateRandomCharacterMethod)
            val character = gameModeServiceTestClass.invokeMethodWithoutArgs(
                invokeData = invokeData,
                isPrivate = true,
            ).toString()
            assert(
                character in PhotoCharacter.values()
                    .map { it.name }) { "The method ${gameModeServiceTestClass.name} should generate a random character from PhotoCharacter enum class!" }
            generatedCharacters.add(character)
        }
        assert(generatedCharacters.size > 1) { "The method ${gameModeServiceTestClass.name} should generate random characters, but the current implementation always generates ${generatedCharacters.first()}" }
    }

    @Test
    fun generateListOfCharactersMethodTest() {
        testCollectionGeneration(generateListOfCharactersMethod, CollectionType.List)
    }

    private fun String.checkCollectionType(collectionType: CollectionType, errorMessage: String) {
        when (collectionType) {
            CollectionType.List, CollectionType.Set -> assert(this.startsWith("[") && this.endsWith("]")) { errorMessage }
            else -> assert(this.startsWith("{") && this.endsWith("}")) { errorMessage }
        }
    }

    private fun testCollectionGeneration(
        method: TestMethod,
        collectionType: CollectionType,
        uniqCheck: (String) -> Unit = { }
    ) {
        val generatedListsOfCharacters = mutableSetOf<String>()
        repeat(100) {
            val invokeData = TestMethodInvokeData(gameModeServiceTestClass, method)
            val characters = gameModeServiceTestClass.invokeMethodWithoutArgs(
                invokeData = invokeData,
            ).toString()
            val errorMessage =
                "The method ${method.name} should generate a ${collectionType.name.uppercase()} of characters."
            characters.checkCollectionType(collectionType, errorMessage)
            val numberOfCharacters = characters.split(",").size
            assert(numberOfCharacters == 12) { "The method ${method.name} should generate a ${collectionType.name} of 12 characters, now the generated ${collectionType.name} contains $numberOfCharacters characters" }
            generatedListsOfCharacters.add(characters)
            uniqCheck(characters)
        }
        assert(generatedListsOfCharacters.size > 1) { "The method ${method.name} should generate a ${collectionType.name} of random characters, but the current implementation always generates ${generatedListsOfCharacters.first()}" }
    }

    @Test
    fun hairTypeTestClassTest() {
        val clazz = hairTypeTestClass.checkBaseDefinition()
        hairTypeTestClass.checkFieldsDefinition(clazz, toCheckDeclaredFieldsSize = false)
        hairTypeTestClass.checkNoConstructors(clazz)
        hairTypeTestClass.checkEnumEntryDefinition(clazz)
    }

    @Test
    fun colorTestClassTest() {
        val clazz = colorTestClass.checkBaseDefinition()
        colorTestClass.checkFieldsDefinition(clazz, toCheckDeclaredFieldsSize = false)
        colorTestClass.checkNoConstructors(clazz)
        colorTestClass.checkEnumEntryDefinition(clazz)
    }

    @Test
    fun accessoryTestClassTest() {
        val clazz = accessoryTestClass.checkBaseDefinition()
        accessoryTestClass.checkFieldsDefinition(clazz, toCheckDeclaredFieldsSize = false)
        accessoryTestClass.checkNoConstructors(clazz)
        accessoryTestClass.checkEnumEntryDefinition(clazz)
    }
}