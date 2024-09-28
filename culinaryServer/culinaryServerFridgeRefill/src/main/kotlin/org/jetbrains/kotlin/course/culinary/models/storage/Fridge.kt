package org.jetbrains.kotlin.course.culinary.models.storage

import org.jetbrains.kotlin.course.culinary.models.food.FruitType
import org.jetbrains.kotlin.course.culinary.models.food.Vegetable
import org.jetbrains.kotlin.course.culinary.models.food.VegetableType

// Represents a fridge with the ingredients for cooking
interface Fridge {
    fun getVegetable(what: VegetableType): Vegetable

    fun getAllVegetables(): Collection<Vegetable>

    fun getBasketOf(type: FruitType): Basket
}
