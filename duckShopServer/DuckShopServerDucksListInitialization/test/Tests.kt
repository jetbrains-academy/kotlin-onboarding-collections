import org.jetbrains.academy.test.system.core.findMethod
import org.jetbrains.academy.test.system.core.models.method.TestMethodInvokeData
import org.jetbrains.kotlin.course.duck.shop.duck.Duck
import org.jetbrains.kotlin.course.duck.shop.utils.MAX_NUMBER_OF_DUCKS
import org.junit.jupiter.api.Test
import java.lang.reflect.InvocationTargetException

class Test {
    @Test
    fun generateRandomDuckMethodTest() {
        val clazz = duckClass.getJavaClass()
        val method = clazz.methods.findMethod(generateRandomDuckMethod)
        val ducks = mutableSetOf<String>()
        repeat(100) {
            try {
                val duck = method.invoke(null).toString()
                assert(duck.isNotEmpty()) { "The method ${generateRandomDuckMethod.name} should generate a random duck, but it generates an empty output." }
                ducks.add(duck)
            } catch (e: InvocationTargetException) {
                assert(false) { "The method ${generateRandomDuckMethod.name} should generate a random duck, but it throws an exception" }
            }
        }
        assert(ducks.size > 1) { "The method ${generateRandomDuckMethod.name} was invoked 100 times, it always generates ${ducks.first()}." }
    }

    @Test
    fun generateListOfDucksMethodTest() {
        val invokeData = TestMethodInvokeData(gameModeServiceTestClass, generateListOfDucksMethod)
        val possibleDucks = Duck.entries
        val generatedDucks = mutableSetOf<List<Duck>>()
        repeat(100) {
            try {
                val output = gameModeServiceTestClass.invokeMethodWithoutArgs(invokeData)
                val ducks: List<Duck> = (output as? List<Duck>) ?: run {
                    assert(false) { "The method ${generateListOfDucksMethod.name} should generate a list of random ducks, but it generates $output" }
                    emptyList()
                }
                assert(ducks.size == MAX_NUMBER_OF_DUCKS) { "The method ${generateListOfDucksMethod.name} should generate a list of random ducks with $MAX_NUMBER_OF_DUCKS ducks. The size of the current list is ${ducks.size}" }
                assert(ducks.all { it in possibleDucks } ) { "The method ${generateListOfDucksMethod.name} should generate a list of random ducks, but it contains an extra element, that is not a value from the Duck enum class." }
                generatedDucks.add(ducks)
            } catch (e: InvocationTargetException) {
                assert(false) { "The method ${generateListOfDucksMethod.name} should generate a list of random ducks, but it throws an exception" }
            }
        }
        assert(generatedDucks.size > 1) { "The method ${generateListOfDucksMethod.name} was invoked 100 times, it always generates ${generatedDucks.first()}." }
    }
}
