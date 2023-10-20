import org.jetbrains.academy.test.system.core.models.method.TestMethod
import org.jetbrains.academy.test.system.core.models.method.TestMethodInvokeData
import org.jetbrains.kotlin.course.old.school.photo.Accessory
import org.jetbrains.kotlin.course.old.school.photo.PhotoCharacter
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.lang.reflect.InvocationTargetException
import java.util.*

class Test {
    companion object {
        private val possibleExpectedColors =
            listOf("white", "blue", "orange", "yellow", "pink", "purple", "green", "gray")
        private val possibleCharactersExpected = PhotoCharacter.entries.map { it.name }
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
            val currentCharacters = PhotoCharacter.entries.shuffled().take(12)
            val currentInput = currentCharacters.map { it.name.lowercase() }
            // TODO: replace with input/output data to hide the implementation??
            val expectedResult = expectedResultFunction(currentCharacters)
            val actualResult = try {
                gameFunctionsServiceTestClass.invokeMethodWithArgs(
                    args = arrayOf(currentInput),
                    invokeData = invokeData,
                ).toString()
            } catch (e: InvocationTargetException) {
                assertTrue(false) { "Try to invoke function ${testMethod.name} with argument $currentInput, but got an unexpected error!" }
            }
            assertTrue(expectedResult.toString() == actualResult) { "The method ${testMethod.name} with argument $currentInput should return $expectedResult, but the current implementation returns $actualResult" }
        }
    }
    @Test
    fun findPhotoMethodBaseTest() {
        repeat(100) {
            val invokeData = TestMethodInvokeData(gameFunctionsServiceTestClass, findPhotoMethod)
            val characters = PhotoCharacter.entries.toTypedArray()
            characters.shuffle()
            characters.forEach { photoCharacter ->
                val background = photoCharacter.backgroundColor.name
                val photoCharacterActual = invokeData.callFindPhotoMethod(characters.toList(), background)
                val expectedPhotoCharacter =
                    characters.find { it.backgroundColor.toString().lowercase() == background.lowercase() }
                assertTrue(expectedPhotoCharacter!!.name == photoCharacterActual) { "The method ${findPhotoMethod.name} with arguments: $possibleCharactersArgs, and $background should return ${photoCharacter.name}, but the current implementation returns $photoCharacterActual." }
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
            assertTrue(false) { "Try to invoke function ${findPhotoMethod.name} with arguments: $possibleCharactersArgs, and $background but got an unexpected error!" }
            null
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
                assertTrue(false) { "Try to invoke function ${toColorMethod.name} with argument $expectedColor, but got an unexpected error!" }
            }
            val expectedOutput = expectedColor.replaceFirstChar { it.titlecase(Locale.getDefault()) }
            assertTrue(expectedOutput == actualColor) { "The function ${toColorMethod.name} should return $expectedOutput for input $expectedColor." }
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
            assertTrue(false) { "Try to invoke function ${toPhotoCharactersMethod.name} with argument $toPhotoCharactersMethod, but got an unexpected error!" }
        }
        assertTrue(possibleCharactersExpected.toString() == possibleCharactersActual) { "The method ${toPhotoCharactersMethod.name} for the list of names $possibleCharactersArgs should return $possibleCharactersExpected, but the current implementation returns $possibleCharactersActual" }
    }

    @Test
    fun getAllPossibleColorsMethodTest() {
        val invokeData = TestMethodInvokeData(gameFunctionsServiceTestClass, getAllPossibleColorsMethod)
        val colors = gameFunctionsServiceTestClass.invokeMethodWithoutArgs(
            invokeData = invokeData,
        ).toString()
        val colorsParsed = colors.removePrefix("[").removeSuffix("]").split(",").map { it.trim() }
        assertTrue(colorsParsed.size == possibleExpectedColors.size) { "The method ${getAllPossibleColorsMethod.name} should return ${possibleExpectedColors.size} different colors!" }
        possibleExpectedColors.forEach { expectedColor ->
            assertTrue(expectedColor in colorsParsed) { "The method ${getAllPossibleColorsMethod.name} should return ${possibleExpectedColors.size} different colors, including $expectedColor!" }
        }
    }
}