package org.jetbrains.kotlin.course.culinary.models

import org.jetbrains.kotlin.course.culinary.models.food.CutVegetable
import org.jetbrains.kotlin.course.culinary.models.food.Fruit
import org.jetbrains.kotlin.course.culinary.models.food.Ingredient
import org.jetbrains.kotlin.course.culinary.models.food.Vegetable
import org.jetbrains.kotlin.course.culinary.models.storage.Basket

interface Kitchen : Resettable {
    fun <T : Ingredient> put(item: T): T

    fun cut(vegetable: Vegetable): CutVegetable

    fun <T : Ingredient> take(item: T): T

    fun takeFromBasket(basket: Basket): Fruit

    fun checkFresh(vegetable: Vegetable): Boolean
}
