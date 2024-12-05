import org.jetbrains.academy.test.system.core.findMethod
import org.jetbrains.kotlin.course.culinary.implementation.storage.FridgeImpl
import org.jetbrains.kotlin.course.culinary.implementation.storage.FridgeImpl.RANDOM_FRESH_VEGETABLES_NUMBER
import org.jetbrains.kotlin.course.culinary.implementation.storage.FridgeImpl.RANDOM_VEGETABLES_NUMBER
import org.jetbrains.kotlin.course.culinary.models.food.Vegetable
import org.junit.jupiter.api.Test
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

class Test {
    private fun handleInvocationException(e: InvocationTargetException, method: Method) {
        val errorMessage = e.targetException.message
        errorMessage?.let { m ->
            if ("An operation is not implemented" in m) {
                assert(false) { "Can not invoke method ${method.name}. Please, add an implementation!" }
            }
        }
        val messageForStudent = errorMessage?.let{ ": $it" } ?: ""
        assert(false) { "Ooops! Something went wrong during invocation ${method.name} method$messageForStudent" }
    }

    @Test
    fun generateRandomVegetablesMethodTest() {
        val vegetables = generateRandomVegetables()
        val expectedNumOfVegetables = RANDOM_VEGETABLES_NUMBER + RANDOM_FRESH_VEGETABLES_NUMBER
        assert(vegetables.size == expectedNumOfVegetables) { "You need to generate $expectedNumOfVegetables vegetables" }
        assert(vegetables.toSet().size > 1) { "You need to generate different random vegetables!" }
        assert(vegetables.filter{ it.isFresh }.size >= RANDOM_FRESH_VEGETABLES_NUMBER) { "You need to generate at least $RANDOM_FRESH_VEGETABLES_NUMBER fresh vegetables" }
    }

    private fun generateRandomVegetables(): List<Vegetable> {
        val clazz = fridgeImplTestClass.checkBaseDefinition()
        val method = clazz.declaredMethods.findMethod(generateRandomVegetablesMethod)
        val instance = clazz.fields.find { it.name == "INSTANCE" }?.get(null)

        return try {
            fridgeImplTestClass.invokeMethodWithoutArgs(clazz, instance, method, true) as List<Vegetable>
        } catch(e: InvocationTargetException) {
            handleInvocationException(e, method)
            emptyList()
        }
    }

    @Test
    fun refillMethodTest() {
        val clazz = fridgeImplTestClass.checkBaseDefinition()
        val method = clazz.declaredMethods.findMethod(refillMethod)
        val instance = clazz.fields.find { it.name == "INSTANCE" }?.get(null)

        val vegetablesNumInitial = FridgeImpl.vegetables.size
        FridgeImpl.vegetables.addAll(generateRandomVegetables())
        val vegetablesNumBeforeRefill = FridgeImpl.vegetables.size
        assert(vegetablesNumBeforeRefill - vegetablesNumInitial > 0) { "Method ${generateRandomVegetablesMethod.name} should generate random vegetables!" }

        try {
            method.invoke(instance)
        } catch(e: InvocationTargetException) {
            handleInvocationException(e, method)
        }

        val vegetablesNumAfterRefill = FridgeImpl.vegetables.size
        val expectedNumOfVegetables = RANDOM_VEGETABLES_NUMBER + RANDOM_FRESH_VEGETABLES_NUMBER
        assert(vegetablesNumAfterRefill == expectedNumOfVegetables) { "Method ${method.name} should add $expectedNumOfVegetables vegetables into the fridge!" }
    }
}
