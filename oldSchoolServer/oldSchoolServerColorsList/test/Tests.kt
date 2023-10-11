import org.jetbrains.academy.test.system.core.models.method.TestMethodInvokeData
import org.junit.jupiter.api.Test

class Test {
    companion object {
        private val possibleExpectedColors =
            listOf("white", "blue", "orange", "yellow", "pink", "purple", "green", "gray")
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