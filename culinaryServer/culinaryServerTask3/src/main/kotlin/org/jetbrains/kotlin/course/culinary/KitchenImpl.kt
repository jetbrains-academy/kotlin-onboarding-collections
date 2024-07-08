package org.jetbrains.kotlin.course.culinary

import culinary.JsAction
import culinary.JsActionType
import culinary.JsItemType
import kotlin.random.Random


internal val actions: MutableList<JsAction> = ArrayList()

internal fun Item.toJsItemType(): JsItemType = when (this) {
    is Vegetable -> {
        when (type) {
            VegetableType.Tomato -> if (isFresh)
                JsItemType.FRESH_TOMATO
            else
                JsItemType.ROT_TOMATO

            VegetableType.Cucumber -> if (isFresh)
                JsItemType.FRESH_CUCUMBER
            else
                JsItemType.ROT_CUCUMBER

            VegetableType.Carrot -> if (isFresh)
                JsItemType.FRESH_CARROT
            else
                JsItemType.ROT_CARROT
        }
    }

    is CutVegetable -> {
        when (type) {
            VegetableType.Tomato -> JsItemType.CUT_TOMATO
            VegetableType.Cucumber -> JsItemType.CUT_CUCUMBER
            VegetableType.Carrot -> JsItemType.CUT_CARROT
        }
    }

    is Basket -> when (type) {
        FruitType.Berry -> JsItemType.BERRY_BASKET
        FruitType.Citrus -> JsItemType.CITRUS_BASKET
    }

    is Fruit -> when (type) {
        FruitType.Berry -> JsItemType.BERRY
        FruitType.Citrus -> JsItemType.CITRUS
    }

    is Spice -> when (type) {
        SpiceType.Salt -> JsItemType.SALT
        SpiceType.Pepper -> JsItemType.PEPPER
        SpiceType.Oregano -> JsItemType.OREGANO
    }
}

internal fun JsAction(type: JsActionType, parameter: Item) = JsAction(type, parameter.toJsItemType())

internal data object FridgeImpl : Fridge {
    internal val vegetables = mutableListOf<Vegetable>()

    init {
        fill()
    }

    internal fun fill() {
        repeat(7) {
            vegetables += Vegetable(VegetableType.entries.random(), Random.nextBoolean())
        }
        repeat(3) {
            vegetables += Vegetable(VegetableType.Tomato, true)
        }
    }

    override fun getVegetable(what: VegetableType): Vegetable {
        val veg = checkNotNull(vegetables.find { it.type == what && it.isFresh }) { "Fresh vegetable $what not found." }
        vegetables.remove(veg)
        return veg
    }

    override fun getAllVegetables(): Collection<Vegetable> {
        check(vegetables.isNotEmpty()) { "No more vegetables left in the fridge" }
        val allVegs = vegetables.shuffled()
        vegetables.clear()
        return allVegs
    }

    override fun getBasketOf(type: FruitType): Basket = Basket(type, Random.nextInt(1, 3))
}

internal data object ShelfImpl : Shelf {
    override fun getSpice(what: SpiceType): Spice = Spice(what)
}

internal data object PotImpl : Pot {
    internal val contents = HashSet<Item>()
    internal var simmering = false
    internal var tastesPerfect = false

    override fun put(vegetable: CutVegetable) {
        require(vegetable !in KitchenCounterImpl.contents) { "One can't put something to the pot before taking it from counter" }

        if (contents.add(vegetable))
            actions.add(JsAction(JsActionType.PUT_IN_POT, vegetable))
        else
            error("You are trying to put the same vegetable $vegetable in the pot twice")
    }

    override fun put(spice: Spice) {
        if (contents.add(spice))
            actions += JsAction(JsActionType.PUT_IN_POT, spice)
        else
            error("You are trying to put the same spice $spice in the pot twice")
    }

    override fun doesTastePerfect(): Boolean {
        if (tastesPerfect) return true
        tastesPerfect = (Random.nextBoolean() && contents.any { it is Spice && it.type == SpiceType.Salt }
                && contents.any { it is Spice && it.type == SpiceType.Pepper }
                && contents.any { it is Spice && it.type == SpiceType.Oregano })
        return tastesPerfect
    }

    override fun simmer() {
        check(!simmering) { "You are already simmering" }
        simmering = true
        actions += JsAction(JsActionType.SIMMER)
    }
}

internal data object SaladBowlImpl : SaladBowl {
    val contents = HashSet<Item>()
    var mixing = false

    override fun add(type: VegetableType, cuts: List<CutVegetable>) {
        requireNotNull(cuts.all { it.type == type }) {
            "One should put the vegetables to the salad bowl grouping them by type"
        }
        for (it in cuts) {
            check(it !in KitchenCounterImpl.contents) {
                "One can't put something to the salad bowl before taking it from counter"
            }
            if (contents.add(it))
                actions += JsAction(JsActionType.ADD_TO_SALAD, it)
            else
                error("You are trying to put the same vegetable $it in the salad bowl twice")
        }
    }

    override fun mix() {
        check(!mixing) { "You are already mixing" }
        mixing = true
        actions += JsAction(JsActionType.MIX_SALAD)
    }
}

internal data object BlenderImpl : Blender {
    val contents: MutableSet<Fruit> = HashSet()
    var blending = false

    override fun blend() {
        check(!blending) { "You are already blending." }
        blending = true
        actions += JsAction(JsActionType.BLEND)
    }

    override fun add(fruit: Fruit) {
        if (contents.add(fruit))
            actions += JsAction(JsActionType.ADD_TO_BLENDER, fruit)
        else
            error("You are trying to put the same fruit $fruit in the blender twice")
    }
}

internal data object KitchenCounterImpl : KitchenCounter {
    val contents: MutableSet<Item> = HashSet()

    override fun <T : Item> put(item: T): T {
        if (contents.add(item))
            actions.add(JsAction(JsActionType.SHOW_ON_COUNTER, item))
        else
            error("You are trying to put the same item $item on the kitchen counter twice")

        return item
    }

    override fun cut(vegetable: Vegetable): CutVegetable {
        require(vegetable in contents) { "Vegetable $vegetable is not on the counter, so can't be cut" }
        require(vegetable.isFresh) { "One can't cut rot vegetable $vegetable" }
        contents -= vegetable
        actions += JsAction(JsActionType.REMOVE_FROM_COUNTER, vegetable)
        val cut = CutVegetable(vegetable.type)
        contents += cut
        actions += JsAction(JsActionType.SHOW_ON_COUNTER, cut)
        return cut
    }

    override fun <T : Item> take(item: T): T {
        if (contents.remove(item)) {
            actions += JsAction(JsActionType.REMOVE_FROM_COUNTER, item)
        } else {
            error("You are trying to take the item that is not on the counter.")
        }
        return item
    }

    override fun takeFromBasket(basket: Basket): Fruit {
        require(basket in contents) { "Basket $basket is not on the counter, so can't be accessed" }
        require(basket.left > 0) { "There are no fruits left in basket $basket" }
        basket.left--

        if (basket.left == 0) {
            actions += JsAction(JsActionType.REMOVE_FROM_COUNTER, basket)
        }

        return Fruit(basket.type)
    }

    override fun checkFresh(vegetable: Vegetable): Boolean {
        require(vegetable in contents) { "Vegetable $vegetable is not on the counter, so can't be checked" }
        return vegetable.isFresh
    }
}

internal fun clearKitchen() {
    FridgeImpl.vegetables.clear()
    FridgeImpl.fill()
    PotImpl.contents.clear()
    SaladBowlImpl.contents.clear()
    BlenderImpl.contents.clear()
    KitchenCounterImpl.contents.clear()
    actions.clear()
}