package org.jetbrains.kotlin.course.culinary.models.cooking

import org.jetbrains.kotlin.course.culinary.models.Resettable
import org.jetbrains.kotlin.course.culinary.models.food.CutVegetable
import org.jetbrains.kotlin.course.culinary.models.food.VegetableType

interface SaladBowl : Resettable {
    fun add(type: VegetableType, cuts: List<CutVegetable>)

    fun mix()
}
