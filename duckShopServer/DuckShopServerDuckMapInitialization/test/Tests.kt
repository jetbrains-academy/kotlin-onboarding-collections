import org.jetbrains.academy.test.system.core.findMethod
import org.jetbrains.academy.test.system.core.models.method.TestMethodInvokeData
import org.jetbrains.kotlin.course.duck.shop.duck.Duck
import org.jetbrains.kotlin.course.duck.shop.duck.getDescription
import org.jetbrains.kotlin.course.duck.shop.utils.MAX_NUMBER_OF_DUCKS
import org.junit.jupiter.api.Test
import java.lang.reflect.InvocationTargetException

class Test {
    @Test
    fun generateMapOfDucksMethodTest() {
        val invokeData = TestMethodInvokeData(gameModeServiceTestClass, generateMapOfDucksMethod)
        val possibleDucks = Duck.entries
        val generatedDucks = mutableSetOf<Map<Duck, String>>()
        repeat(100) {
            try {
                val output = gameModeServiceTestClass.invokeMethodWithoutArgs(invokeData)
                val ducks: Map<Duck, String> = (output as? Map<Duck, String>) ?: run {
                    assert(false) { "The method ${generateMapOfDucksMethod.name} should generate a map of random ducks with their descriptions, but it generates $output" }
                    emptyMap()
                }
                assert(ducks.size == MAX_NUMBER_OF_DUCKS) { "The method ${generateMapOfDucksMethod.name} should generate a map of random ducks with $MAX_NUMBER_OF_DUCKS ducks. The size of the current map is ${ducks.size}" }
                assert(ducks.all { (duck, d) ->
                    duck in possibleDucks && duck.getDescription() == d
                }) { "The method ${generateMapOfDucksMethod.name} should generate a map of random ducks with their descriptions, but it contains an extra element, that is not a value from the Duck enum class or has an incorrect description." }
                generatedDucks.add(ducks)
            } catch (e: InvocationTargetException) {
                assert(false) { "The method ${generateMapOfDucksMethod.name} should generate a map of random ducks with their descriptions, but it throws an exception" }
            }
        }
        assert(generatedDucks.size > 1) { "The method ${generateMapOfDucksMethod.name} was invoked 100 times, it always generates ${generatedDucks.first()}." }
    }

    @Test
    fun generateSetOfDucksMethodTest() {
        val invokeData = TestMethodInvokeData(gameModeServiceTestClass, generateSetOfDucksMethod)
        val possibleDucks = Duck.entries
        val generatedDucks = mutableSetOf<Set<Duck>>()
        repeat(100) {
            try {
                val output = gameModeServiceTestClass.invokeMethodWithoutArgs(invokeData)
                val ducks: Set<Duck> = (output as? Set<Duck>) ?: run {
                    assert(false) { "The method ${generateSetOfDucksMethod.name} should generate a set of random ducks, but it generates $output" }
                    emptySet()
                }
                assert(ducks.size == MAX_NUMBER_OF_DUCKS) { "The method ${generateSetOfDucksMethod.name} should generate a set of random ducks with $MAX_NUMBER_OF_DUCKS ducks. The size of the current set is ${ducks.size}" }
                assert(ducks.all { it in possibleDucks }) { "The method ${generateSetOfDucksMethod.name} should generate a set of random ducks, but it contains an extra element, that is not a value from the Duck enum class." }
                generatedDucks.add(ducks)
            } catch (e: InvocationTargetException) {
                assert(false) { "The method ${generateSetOfDucksMethod.name} should generate a set of random ducks, but it throws an exception" }
            }
        }
        assert(generatedDucks.size > 1) { "The method ${generateSetOfDucksMethod.name} was invoked 100 times, it always generates ${generatedDucks.first()}." }
    }

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
                assert(ducks.all { it in possibleDucks }) { "The method ${generateListOfDucksMethod.name} should generate a list of random ducks, but it contains an extra element, that is not a value from the Duck enum class." }
                generatedDucks.add(ducks)
            } catch (e: InvocationTargetException) {
                assert(false) { "The method ${generateListOfDucksMethod.name} should generate a list of random ducks, but it throws an exception" }
            }
        }
        assert(generatedDucks.size > 1) { "The method ${generateListOfDucksMethod.name} was invoked 100 times, it always generates ${generatedDucks.first()}." }
    }
}
