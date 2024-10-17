import org.jetbrains.academy.test.system.core.findMethod
import org.jetbrains.academy.test.system.core.invokeWithArgs
import org.jetbrains.academy.test.system.core.invokeWithoutArgs
import org.jetbrains.academy.test.system.core.models.classes.ConstructorGetter
import org.jetbrains.kotlin.course.culinary.game.actions
import org.jetbrains.kotlin.course.culinary.game.clearActions
import org.jetbrains.kotlin.course.culinary.game.recipes.NUMBER_OF_TOMATOES
import org.jetbrains.kotlin.course.culinary.implementation.storage.FridgeImpl
import org.jetbrains.kotlin.course.culinary.implementation.storage.FridgeImpl.RANDOM_FRESH_VEGETABLES_NUMBER
import org.jetbrains.kotlin.course.culinary.implementation.storage.FridgeImpl.RANDOM_VEGETABLES_NUMBER
import org.jetbrains.kotlin.course.culinary.models.ItemType
import org.jetbrains.kotlin.course.culinary.models.action.Action
import org.jetbrains.kotlin.course.culinary.models.action.ActionType
import org.jetbrains.kotlin.course.culinary.models.food.SpiceType
import org.jetbrains.kotlin.course.culinary.models.food.Vegetable
import org.jetbrains.kotlin.course.culinary.models.food.VegetableType
import org.junit.jupiter.api.Test
import java.lang.reflect.InvocationTargetException

class Test {
    @Test
    fun generateSpicesMethodTest() {
        val spices = generateSpices().take(5).toList()
        if (spices.isEmpty()) {
           assert(false) { "The method ${generateSpicesMethod.name} should generate random spices! Now you always generate an empty sequence!" }
        }
        assert(spices.toSet().size > 1) { "The method ${generateSpicesMethod.name} should generate random spices! Now you always generate ${spices.first()}" }
    }

    private fun generateSpices(): Sequence<SpiceType> {
        val clazz = tomatoSoupKtTestClass.checkBaseDefinition()
        val method = clazz.declaredMethods.findMethod(generateSpicesMethod)

        return try {
            method.invokeWithoutArgs(clazz = clazz) as Sequence<SpiceType>
        } catch(e: InvocationTargetException) {
            assert(false) { "Can not invoke method ${method.name}. Please, add an implementation!" }
            emptySequence()
        }
    }

    @Test
    fun addSpicesMethodTest() {
        clearActions()
        val clazz = tomatoSoupKtTestClass.checkBaseDefinition()
        val method = clazz.declaredMethods.findMethod(addSpicesMethod)

        try {
            method.invoke(clazz, generateSpices())
        } catch(e: InvocationTargetException) {
            assert(false) { "Can not invoke method ${method.name}. Please, add an implementation!" }
        }

        assert(actions.isNotEmpty() && actions.all{ it.type == ActionType.PUT_IN_POT }) { "The ${method.name} should generate spices and add them into the pot." }
    }

    @Test
    fun getTomatoesForSoupMethodTest() {
        val clazz = tomatoSoupKtTestClass.checkBaseDefinition()
        val method = clazz.declaredMethods.findMethod(getTomatoesForSoupMethod)
        FridgeImpl.vegetables.addAll(generateTomatoesForSoup())

        val vegetables: List<Vegetable> = try {
            method.invokeWithoutArgs(clazz = clazz) as List<Vegetable>
        } catch(e: InvocationTargetException) {
            assert(false) { "Can not invoke method ${method.name}. Please, add an implementation!" }
            emptyList()
        }

        assert(vegetables.all{ it.type == VegetableType.Tomato && it.isFresh }) { "Method ${method.name} should generate only fresh tomatoes" }
        assert(vegetables.size == NUMBER_OF_TOMATOES) { "Method ${method.name} should generate ${NUMBER_OF_TOMATOES} tomatoes, currently it generates ${vegetables.size} tomatoes" }
    }

    @Test
    fun prepareTomatoesMethodTest() {
        clearActions()
        val clazz = tomatoSoupKtTestClass.checkBaseDefinition()
        val method = clazz.declaredMethods.findMethod(prepareTomatoesMethod)

        try {
            method.invoke(clazz, generateTomatoesForSoup())
        } catch(e: InvocationTargetException) {
            assert(false) { "Can not invoke method ${method.name}. Please, add an implementation!" }
        }

        val expectedActions = buildList {
            addAll(List(NUMBER_OF_TOMATOES) { Action(ActionType.SHOW_ON_COUNTER, ItemType.FRESH_TOMATO) })
            repeat(NUMBER_OF_TOMATOES) {
                add(Action(ActionType.CUT_ON_COUNTER, ItemType.FRESH_TOMATO))
            }
            repeat(NUMBER_OF_TOMATOES) {
                addAll(listOf(
                    Action(ActionType.PUT_IN_POT, ItemType.CUT_TOMATO)
                ))
            }
        }

        assert(actions == expectedActions) { "The ${method.name} should cook a tomato soup in the following algorithm: put each tomato into the kitchen, then cut each tomato, then take each cut tomato and put each tomato into the pot." }
    }

    private fun generateTomatoesForSoup() = List(NUMBER_OF_TOMATOES) { Vegetable(VegetableType.Tomato, true) }

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
            assert(false) { "Can not invoke method ${method.name}. Please, add an implementation!" }
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
            assert(false) { "Can not invoke method ${method.name}. Please, add an implementation!" }
        }

        val vegetablesNumAfterRefill = FridgeImpl.vegetables.size
        val expectedNumOfVegetables = RANDOM_VEGETABLES_NUMBER + RANDOM_FRESH_VEGETABLES_NUMBER
        assert(vegetablesNumAfterRefill == expectedNumOfVegetables) { "Method ${method.name} should add $expectedNumOfVegetables vegetables into the fridge!" }
    }
}
