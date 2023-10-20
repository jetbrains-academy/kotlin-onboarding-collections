import org.jetbrains.academy.test.system.core.findMethod
import org.jetbrains.academy.test.system.core.models.classes.TestClass
import org.jetbrains.academy.test.system.core.models.method.TestMethod
import org.jetbrains.academy.test.system.core.models.method.TestMethodInvokeData
import org.jetbrains.kotlin.course.duck.shop.duck.Duck
import org.jetbrains.kotlin.course.duck.shop.duck.getDescription
import org.jetbrains.kotlin.course.duck.shop.utils.MAX_NUMBER_OF_DUCKS
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.lang.reflect.InvocationTargetException

class Test {
    private fun <D, T: Collection<D>> checkOutputForCollection(ducks: T, currentDucks: T, errorPrefix:String, collectionType: String, output: Any): D {
        assertTrue(ducks.size == currentDucks.size - 1) { "$errorPrefix for the $collectionType $currentDucks it returns $output" }
        assertTrue(ducks.all { it in currentDucks }) { "$errorPrefix the output $output contains a duck that was not included in the initial list: $currentDucks" }
        val currentRemovedDucks = currentDucks.minus(ducks.toSet())
        assertTrue(currentRemovedDucks.size == 1) { "$errorPrefix for the list $currentDucks it returns $output" }
        return currentRemovedDucks.first()
    }

    private fun <D, T: Collection<D>> testRemoveDuckFromCollection(
        testClass: TestClass,
        testMethod: TestMethod,
        collectionType: String,
        currentDucks: T,
        convertOutputToDucks: (Any, String, T) -> T
    ) {
        val invokeData = TestMethodInvokeData(testClass, testMethod)
        val errorPrefix = "The method ${testMethod.name} should remove a random duck from a $collectionType of ducks, but "
        val removedDucks = mutableListOf<D>()
        repeat(100) {
            try {
                val output = testClass.invokeMethodWithArgs(currentDucks, invokeData = invokeData)
                val ducks = convertOutputToDucks(output, errorPrefix, currentDucks)
                removedDucks.add(checkOutputForCollection(ducks, currentDucks, errorPrefix, collectionType, output))
            } catch (e: InvocationTargetException) {
                assertTrue(false) { "$errorPrefix it throws an exception" }
            }
        }
        assertTrue(removedDucks.size > 1) { "The method ${testMethod.name} was invoked 100 times, it always removes ${removedDucks.first()}." }
    }

    @Test
    fun removeRandomDuckFromListMethodTest() {
        testRemoveDuckFromCollection(
            gameChangeFunctionsServiceTestClass,
            removeRandomDuckListMethod,
            "list",
            Duck.entries.toList().shuffled()
        ) { output, errorPrefix, currentDucks ->
            (output as? List<Duck>) ?: run {
                assertTrue(false) { "$errorPrefix for the list $currentDucks it returns $output" }
                emptyList()
            }
        }
    }

    @Test
    fun removeRandomDuckFromSetMethodTest() {
        testRemoveDuckFromCollection(
            gameChangeFunctionsServiceTestClass,
            removeRandomDuckSetMethod,
            "set",
            Duck.entries.toList().shuffled().toSet()
        ) { output, errorPrefix, currentDucks ->
            (output as? Set<Duck>) ?: run {
                assertTrue(false) { "$errorPrefix for the set $currentDucks it returns $output" }
                emptySet()
            }
        }
    }

    @Test
    fun removeRandomDuckFromMapMethodTest() {
        val invokeData = TestMethodInvokeData(gameChangeFunctionsServiceTestClass, removeRandomDuckMapMethod)
        val errorPrefix = "The method ${removeRandomDuckMapMethod.name} should remove a random duck from a map of ducks, but "
        val removedDucks = mutableListOf<Pair<Duck, String>>()
        val possibleDucks = Duck.entries.toList()
        repeat(100) {
            val currentDucks = possibleDucks.shuffled().associateWith { it.getDescription() }
            try {
                val output = gameChangeFunctionsServiceTestClass.invokeMethodWithArgs(currentDucks, invokeData = invokeData)
                val ducks: Map<Duck, String> = (output as? Map<Duck, String>) ?: run {
                    assertTrue(false) { "$errorPrefix for the map $currentDucks it returns $output" }
                    emptyMap()
                }
                assertTrue(ducks.size == currentDucks.size - 1) { "$errorPrefix for the map $currentDucks it returns $output" }
                assertTrue(ducks.all { (duck, d) ->
                    duck in currentDucks && currentDucks[duck] == d
                }) { "$errorPrefix the output $output contains a duck with its description that was not included in the initial list: $currentDucks" }
                val currentRemovedDucks = currentDucks.keys.minus(ducks.keys.toSet())
                assertTrue(currentRemovedDucks.size == 1) { "$errorPrefix for the map $currentDucks it returns $output" }
                val currentRemovedDuck = currentRemovedDucks.first()
                removedDucks.add(Pair(currentRemovedDuck, currentDucks[currentRemovedDuck]!!))
            } catch (e: InvocationTargetException) {
                assertTrue(false) { "$errorPrefix it throws an exception" }
            }
        }
        assertTrue(removedDucks.size > 1) { "The method ${removeRandomDuckMapMethod.name} was invoked 100 times, it always removes ${removedDucks.first()}." }
    }

    @Test
    fun generateMapOfDucksMethodTest() {
        val invokeData = TestMethodInvokeData(gameModeServiceTestClass, generateMapOfDucksMethod)
        val possibleDucks = Duck.entries
        val generatedDucks = mutableSetOf<Map<Duck, String>>()
        repeat(100) {
            try {
                val output = gameModeServiceTestClass.invokeMethodWithoutArgs(invokeData)
                val ducks: Map<Duck, String> = (output as? Map<Duck, String>) ?: run {
                    assertTrue(false) { "The method ${generateMapOfDucksMethod.name} should generate a map of random ducks with their descriptions, but it generates $output" }
                    emptyMap()
                }
                assertTrue(ducks.size == MAX_NUMBER_OF_DUCKS) { "The method ${generateMapOfDucksMethod.name} should generate a map of random ducks with $MAX_NUMBER_OF_DUCKS ducks. The size of the current map is ${ducks.size}" }
                assertTrue(ducks.all { (duck, d) ->
                    duck in possibleDucks && duck.getDescription() == d
                }) { "The method ${generateMapOfDucksMethod.name} should generate a map of random ducks with their descriptions, but it contains an extra element, that is not a value from the Duck enum class or has an incorrect description." }
                generatedDucks.add(ducks)
            } catch (e: InvocationTargetException) {
                assertTrue(false) { "The method ${generateMapOfDucksMethod.name} should generate a map of random ducks with their descriptions, but it throws an exception" }
            }
        }
        assertTrue(generatedDucks.size > 1) { "The method ${generateMapOfDucksMethod.name} was invoked 100 times, it always generates ${generatedDucks.first()}." }
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
                    assertTrue(false) { "The method ${generateSetOfDucksMethod.name} should generate a set of random ducks, but it generates $output" }
                    emptySet()
                }
                assertTrue(ducks.size == MAX_NUMBER_OF_DUCKS) { "The method ${generateSetOfDucksMethod.name} should generate a set of random ducks with $MAX_NUMBER_OF_DUCKS ducks. The size of the current set is ${ducks.size}" }
                assertTrue(ducks.all { it in possibleDucks }) { "The method ${generateSetOfDucksMethod.name} should generate a set of random ducks, but it contains an extra element, that is not a value from the Duck enum class." }
                generatedDucks.add(ducks)
            } catch (e: InvocationTargetException) {
                assertTrue(false) { "The method ${generateSetOfDucksMethod.name} should generate a set of random ducks, but it throws an exception" }
            }
        }
        assertTrue(generatedDucks.size > 1) { "The method ${generateSetOfDucksMethod.name} was invoked 100 times, it always generates ${generatedDucks.first()}." }
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
                    assertTrue(false) { "The method ${generateListOfDucksMethod.name} should generate a list of random ducks, but it generates $output" }
                    emptyList()
                }
                assertTrue(ducks.size == MAX_NUMBER_OF_DUCKS) { "The method ${generateListOfDucksMethod.name} should generate a list of random ducks with $MAX_NUMBER_OF_DUCKS ducks. The size of the current list is ${ducks.size}" }
                assertTrue(ducks.all { it in possibleDucks }) { "The method ${generateListOfDucksMethod.name} should generate a list of random ducks, but it contains an extra element, that is not a value from the Duck enum class." }
                generatedDucks.add(ducks)
            } catch (e: InvocationTargetException) {
                assertTrue(false) { "The method ${generateListOfDucksMethod.name} should generate a list of random ducks, but it throws an exception" }
            }
        }
        assertTrue(generatedDucks.size > 1) { "The method ${generateListOfDucksMethod.name} was invoked 100 times, it always generates ${generatedDucks.first()}." }
    }
}
