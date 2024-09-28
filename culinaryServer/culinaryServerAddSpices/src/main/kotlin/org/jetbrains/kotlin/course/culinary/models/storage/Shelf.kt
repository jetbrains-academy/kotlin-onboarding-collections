package org.jetbrains.kotlin.course.culinary.models.storage

import org.jetbrains.kotlin.course.culinary.models.food.Spice
import org.jetbrains.kotlin.course.culinary.models.food.SpiceType

// Represents a shelf with spices
interface Shelf {
    fun getSpice(what: SpiceType): Spice
}
