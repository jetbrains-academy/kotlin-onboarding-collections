import org.jetbrains.academy.test.system.core.findMethod
import org.jetbrains.academy.test.system.core.invokeWithoutArgs
import org.jetbrains.kotlin.course.culinary.converters.buildAction
import org.jetbrains.kotlin.course.culinary.game.actions
import org.jetbrains.kotlin.course.culinary.game.clearActions
import org.jetbrains.kotlin.course.culinary.game.recipes.NUMBER_OF_TOMATOES
import org.jetbrains.kotlin.course.culinary.game.recipes.NUM_VEGETABLES_FOR_SALAD
import org.jetbrains.kotlin.course.culinary.implementation.storage.FridgeImpl
import org.jetbrains.kotlin.course.culinary.implementation.storage.FridgeImpl.RANDOM_FRESH_VEGETABLES_NUMBER
import org.jetbrains.kotlin.course.culinary.implementation.storage.FridgeImpl.RANDOM_VEGETABLES_NUMBER
import org.jetbrains.kotlin.course.culinary.models.ItemType
import org.jetbrains.kotlin.course.culinary.models.action.Action
import org.jetbrains.kotlin.course.culinary.models.action.ActionType
import org.jetbrains.kotlin.course.culinary.models.food.*
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
    fun getFruitsForSmoothieMethodTest() {
        clearActions()
        val fruits = getFruitsForSmoothie()

        assert(fruits.toSet().size == 2) { "The ${getFruitsForSmoothieMethod.name} method should return a lit of Citrus and Berry fruits only" }
        val expectedActions = buildList{
            add(Action(ActionType.SHOW_ON_COUNTER, ItemType.BERRY_BASKET))
            add(Action(ActionType.SHOW_ON_COUNTER, ItemType.CITRUS_BASKET))
        }
        assert(expectedActions == actions) { "The ${getFruitsForSmoothieMethod.name} method should return a lit of fruits for smoothie, sorted by sugarContent amount" }
    }

    private fun getFruitsForSmoothie(): List<Fruit> {
        val clazz = smoothieKtTestClass.checkBaseDefinition()
        val method = clazz.declaredMethods.findMethod(getFruitsForSmoothieMethod)

        return try {
            method.invokeWithoutArgs(clazz = clazz) as List<Fruit>
        } catch(e: InvocationTargetException) {
            handleInvocationException(e, method)
            emptyList()
        }
    }

    @Test
    fun cookSmoothieMethodTest() {
        val fruits = getFruitsForSmoothie()

        clearActions()
        val clazz = smoothieKtTestClass.checkBaseDefinition()
        val method = clazz.declaredMethods.findMethod(cookSmoothieMethod)
        try {
            method(clazz, fruits)
        } catch(e: InvocationTargetException) {
            handleInvocationException(e, method)
        }

        val expectedActions = fruits.map{ buildAction(ActionType.ADD_TO_BLENDER, it) }
        assert(expectedActions + Action(ActionType.BLEND) == actions) { "The ${method.name} method should add to the blender all fruits and then blend them" }
    }

    @Test
    fun getFreshVegetablesMethodTest() {
        val randomVegetables = fillFridge()
        val vegetables = getFreshVegetables()
        assert(randomVegetables.filter{ it.isFresh }.sortedBy { it.type } == vegetables.sortedBy { it.type }) { "The ${getFreshVegetablesMethod.name} should return all fresh vegetables from the fridge." }
        assert(FridgeImpl.vegetables.none { it.isFresh }) { "The ${getFreshVegetablesMethod.name} should return all fresh vegetables from the fridge." }
    }

    private fun fillFridge(): List<Vegetable> {
        val randomVegetables = generateRandomVegetables()
        FridgeImpl.vegetables.clear()
        FridgeImpl.vegetables.addAll(randomVegetables)
        return randomVegetables
    }

    private fun getFreshVegetables(): List<Vegetable> {
        val clazz = saladKtTestClass.checkBaseDefinition()
        val method = clazz.declaredMethods.findMethod(getFreshVegetablesMethod)

        return try {
            method.invokeWithoutArgs(clazz = clazz) as List<Vegetable>
        } catch(e: InvocationTargetException) {
            handleInvocationException(e, method)
            emptyList()
        }
    }

    private fun cutVegetablesInvoke(vegetables: List<Vegetable>): List<CutVegetable> {
        val clazz = saladKtTestClass.checkBaseDefinition()
        val method = clazz.declaredMethods.findMethod(cutMethod)

        return try {
            method(clazz, vegetables) as List<CutVegetable>
        } catch(e: InvocationTargetException) {
            handleInvocationException(e, method)
            emptyList()
        }
    }

    @Test
    fun cutMethodTest() {
        clearActions()
        fillFridge()

        val vegetables = getFreshVegetables()
        val expectedVegetables = vegetables.take(NUM_VEGETABLES_FOR_SALAD).map{ CutVegetable(it.type) }

        val cutVegetables = cutVegetablesInvoke(vegetables)

        assert(expectedVegetables.size == cutVegetables.size) { "The ${cutMethod.name} method should take all vegetables from the list of vegetables and cut them!" }
        assert(expectedVegetables.sortedBy { it.type } == cutVegetables.sortedBy { it.type }) { "The ${cutMethod.name} method should take vegetables and cut them!" }

        val expectedActions = buildList {
            addAll(List(vegetables.size) { ActionType.SHOW_ON_COUNTER })
            repeat(vegetables.size) {
                add(ActionType.CUT_ON_COUNTER)
            }
        }
        assert(expectedActions == actions.map{ it.type }) { "The ${cutMethod.name} method should take vegetables and cut them: take each of them from the fridge, and then cut" }
    }

    @Test
    fun mixVegetablesForSaladMethodTest() {
        fillFridge()

        val clazz = saladKtTestClass.checkBaseDefinition()
        val method = clazz.declaredMethods.findMethod(mixVegetablesForSaladMethod)
        val vegetables = getFreshVegetables()
        val cutVegetables = cutVegetablesInvoke(vegetables)

        val expectedActions = cutVegetables.groupBy { it.type }
            .map { (_, cuts) ->
                cuts.map{ c -> buildAction(ActionType.ADD_TO_SALAD, c) }
            }.flatten()

        clearActions()
        try {
            method(clazz, cutVegetables)
        } catch(e: InvocationTargetException) {
            handleInvocationException(e, method)
        }

        assert(expectedActions + Action(ActionType.MIX_SALAD) == actions) { "The ${method.name} method should group cut vegetables by their type, and then for each type add to the salad bowl. Finally it should mix the vegetables." }
    }

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
            handleInvocationException(e, method)
            emptySequence()
        }
    }

    @Test
    fun addSpicesMethodTest() {
        clearActions()
        val clazz = tomatoSoupKtTestClass.checkBaseDefinition()
        val method = clazz.declaredMethods.findMethod(addSpicesMethod)

        try {
            val spices = generateSpices()
            clearActions()
            method.invoke(clazz, spices)
        } catch(e: InvocationTargetException) {
            handleInvocationException(e, method)
        }

        assert(actions.isNotEmpty() && actions.all{ it.type == ActionType.PUT_IN_POT }) { "The ${method.name} should accepts a sequence of spices and add them into the pot. Don't forget about the terminal function in the end!" }
    }

    @Test
    fun getTomatoesForSoupMethodTest() {
        val clazz = tomatoSoupKtTestClass.checkBaseDefinition()
        val method = clazz.declaredMethods.findMethod(getTomatoesForSoupMethod)
        FridgeImpl.vegetables.addAll(generateTomatoesForSoup())

        val vegetables: List<Vegetable> = try {
            method.invokeWithoutArgs(clazz = clazz) as List<Vegetable>
        } catch(e: InvocationTargetException) {
            handleInvocationException(e, method)
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
            handleInvocationException(e, method)
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
