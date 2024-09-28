package org.jetbrains.kotlin.course.culinary.models.storage

import org.jetbrains.kotlin.course.culinary.models.food.FruitType
import org.jetbrains.kotlin.course.culinary.models.food.Ingredient
import org.jetbrains.kotlin.course.culinary.models.food.Spice
import org.jetbrains.kotlin.course.culinary.models.food.SpiceType

// Represents a basket with the fruits for cooking
data class Basket internal constructor(val type: FruitType, val capacity: Int) : Ingredient {
    internal var left = capacity
}
