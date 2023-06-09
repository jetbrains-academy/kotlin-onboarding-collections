import org.jetbrains.academy.test.system.models.classes.ConstructorGetter
import org.jetbrains.academy.test.system.models.method.TestMethod
import org.jetbrains.academy.test.system.models.method.TestMethodInvokeData
import org.jetbrains.kotlin.course.old.school.photo.PhotoCharacter
import org.junit.jupiter.api.Test

class Test {
    companion object {
        private enum class CollectionType {
            List,
            Set,
            Map
            ;
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
            assert(character in PhotoCharacter.values().map { it.name }) { "The method ${gameModeServiceTestClass.name} should generate a random character from PhotoCharacter enum class!" }
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
            val errorMessage = "The method ${method.name} should generate a ${collectionType.name.uppercase()} of characters."
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