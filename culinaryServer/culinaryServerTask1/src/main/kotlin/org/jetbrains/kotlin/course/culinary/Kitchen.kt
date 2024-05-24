package org.jetbrains.kotlin.course.culinary


sealed interface IngredientType

enum class VegetableType : IngredientType {
    Tomato,
    Cucumber,
    Carrot;
}

enum class FruitType(val sugarContent: Int) : IngredientType {
    Berry(sugarContent = 20),
    Citrus(sugarContent = 10);
}

enum class SpiceType : IngredientType {
    Salt,
    Pepper,
    Oregano;
}

data class Basket internal constructor(val type: FruitType, val capacity: Int) : Item {
    internal var left = capacity
    override fun toString(): String = "Basket(type=$type, capacity=$capacity, left=$left)"

}

sealed interface Fridge {
    fun getVegetable(what: VegetableType): Vegetable
    fun getAllVegetables(): Collection<Vegetable>
    fun getBasketOf(type: FruitType): Basket
}

sealed interface Shelf {
    fun getSpice(what: SpiceType): Spice
}

sealed interface Item
class Vegetable internal constructor(val type: VegetableType, internal val isFresh: Boolean) : Item {
    override fun toString(): String = "Vegetable(type=$type, isFresh=$isFresh)"
}

class CutVegetable internal constructor(val type: VegetableType) : Item {
    override fun toString(): String = "CutVegetable(type=$type)"
}

class Fruit internal constructor(val type: FruitType) : Item {
    override fun toString(): String = "Fruit(type=$type)"
}

class Spice internal constructor(val type: SpiceType) : Item {
    override fun toString(): String = "Spice(type=$type)"
}

sealed interface Pot {
    fun put(vegetable: CutVegetable)
    fun put(spice: Spice)
    fun doesTastePerfect(): Boolean
    fun simmer()
}

sealed interface SaladBowl {
    fun add(type: VegetableType, cuts: List<CutVegetable>)
    fun mix()
}

sealed interface Blender {
    fun blend()
    fun add(fruit: Fruit)
}

sealed interface KitchenCounter {
    fun <T : Item> put(item: T): T
    fun cut(vegetable: Vegetable): CutVegetable
    fun <T : Item> take(item: T): T
    fun takeFromBasket(basket: Basket): Fruit
    fun checkFresh(vegetable: Vegetable): Boolean
}

val fridge: Fridge = FridgeImpl
val shelf: Shelf = ShelfImpl
val pot: Pot = PotImpl
val counter: KitchenCounter = KitchenCounterImpl
val blender: Blender = BlenderImpl
val saladBowl: SaladBowl = SaladBowlImpl

