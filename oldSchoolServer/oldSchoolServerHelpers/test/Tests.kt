import org.jetbrains.academy.test.system.core.models.method.TestMethodInvokeData
import org.jetbrains.kotlin.course.old.school.photo.PhotoCharacter
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
}