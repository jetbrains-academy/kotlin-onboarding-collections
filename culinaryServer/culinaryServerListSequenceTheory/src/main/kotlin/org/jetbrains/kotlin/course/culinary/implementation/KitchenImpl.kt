package org.jetbrains.kotlin.course.culinary.implementation

import org.jetbrains.kotlin.course.culinary.game.actions
import org.jetbrains.kotlin.course.culinary.converters.buildAction
import org.jetbrains.kotlin.course.culinary.models.Kitchen
import org.jetbrains.kotlin.course.culinary.models.action.ActionType
import org.jetbrains.kotlin.course.culinary.models.food.CutVegetable
import org.jetbrains.kotlin.course.culinary.models.food.Fruit
import org.jetbrains.kotlin.course.culinary.models.food.Ingredient
import org.jetbrains.kotlin.course.culinary.models.food.Vegetable
import org.jetbrains.kotlin.course.culinary.models.storage.Basket

data object KitchenImpl : Kitchen {
    val filling: MutableList<Ingredient> = mutableListOf()

    override fun <T : Ingredient> put(item: T): T {
        filling.add(item)
        actions.add(buildAction(ActionType.SHOW_ON_COUNTER, item))
        return item
    }

    override fun cut(vegetable: Vegetable): CutVegetable {
        require(vegetable in filling) { "Vegetable $vegetable is not on in the kitchen, so can't be cut" }
        require(vegetable.isFresh) { "One can't cut rot vegetable $vegetable" }
        filling -= vegetable
        actions += buildAction(ActionType.CUT_ON_COUNTER, vegetable)
        val cut = CutVegetable(vegetable.type)
        filling += cut
        return cut
    }

    override fun <T : Ingredient> take(item: T): T {
        require(item in filling) { "You cannot remove an item from the kitchen if this item does not exist!" }
        filling.remove(item)
        return item
    }

    override fun takeFromBasket(basket: Basket): Fruit {
        require(basket in filling) { "Basket $basket is not on the kitchen, so can't be accessed" }
        require(basket.left > 0) { "There are no fruits left in basket $basket" }
        basket.left--
        return Fruit(basket.type)
    }

    override fun checkFresh(vegetable: Vegetable): Boolean {
        require(vegetable in filling) { "Vegetable $vegetable is not on the counter, so can't be checked" }
        return vegetable.isFresh
    }

    override fun reset() {
        filling.clear()
    }
}
