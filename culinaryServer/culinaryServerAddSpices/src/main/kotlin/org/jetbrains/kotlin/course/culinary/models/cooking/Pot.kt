package org.jetbrains.kotlin.course.culinary.models.cooking

import org.jetbrains.kotlin.course.culinary.models.Resettable
import org.jetbrains.kotlin.course.culinary.models.food.CutVegetable
import org.jetbrains.kotlin.course.culinary.models.food.Ingredient

// Represents a pot for the cooking process
interface Pot : Resettable {
    fun <T: Ingredient> put(ingredient: T)

    fun put(vegetable: CutVegetable)

    fun doesTastePerfect(): Boolean

    fun simmer()
}
