package org.jetbrains.kotlin.course.culinary.models.cooking

import org.jetbrains.kotlin.course.culinary.models.Resettable
import org.jetbrains.kotlin.course.culinary.models.food.Fruit

interface Blender : Resettable {
    fun blend()

    fun add(fruit: Fruit)
}
