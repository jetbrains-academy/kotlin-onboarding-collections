package org.jetbrains.kotlin.course.culinary.implementation

import culinary.JsActionType
import org.jetbrains.kotlin.course.culinary.game.actions
import org.jetbrains.kotlin.course.culinary.converters.buildJsAction
import org.jetbrains.kotlin.course.culinary.models.Kitchen
import org.jetbrains.kotlin.course.culinary.models.food.CutVegetable
import org.jetbrains.kotlin.course.culinary.models.food.Fruit
import org.jetbrains.kotlin.course.culinary.models.food.Ingredient
import org.jetbrains.kotlin.course.culinary.models.food.Vegetable
import org.jetbrains.kotlin.course.culinary.models.storage.Basket

data object KitchenImpl : Kitchen {
    val filling: MutableList<Ingredient> = mutableListOf()

    override fun <T : Ingredient> put(item: T): T {
        filling.add(item)
        actions.add(buildJsAction(JsActionType.SHOW_ON_COUNTER, item))
        return item
    }

    override fun cut(vegetable: Vegetable): CutVegetable {
        require(vegetable in filling) { "Vegetable $vegetable is not on in the kitchen, so can't be cut" }
        require(vegetable.isFresh) { "One can't cut rot vegetable $vegetable" }
        filling -= vegetable
        actions += buildJsAction(JsActionType.REMOVE_FROM_COUNTER, vegetable)
        val cut = CutVegetable(vegetable.type)
        filling += cut
        actions += buildJsAction(JsActionType.SHOW_ON_COUNTER, cut)
        return cut
    }

    override fun <T : Ingredient> take(item: T): T {
        require(item in filling) { "You cannot remove an item from the kitchen if this item does not exist!" }
        filling.remove(item)
        actions += buildJsAction(JsActionType.REMOVE_FROM_COUNTER, item)
        return item
    }

    override fun takeFromBasket(basket: Basket): Fruit {
        require(basket in filling) { "Basket $basket is not on the kitchen, so can't be accessed" }
        require(basket.left > 0) { "There are no fruits left in basket $basket" }
        basket.left--

        if (basket.left == 0) {
            actions += buildJsAction(JsActionType.REMOVE_FROM_COUNTER, basket)
        }

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
