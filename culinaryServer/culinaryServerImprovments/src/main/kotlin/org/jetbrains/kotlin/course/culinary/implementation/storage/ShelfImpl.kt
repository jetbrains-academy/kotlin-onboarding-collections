package org.jetbrains.kotlin.course.culinary.implementation.storage

import org.jetbrains.kotlin.course.culinary.models.food.Spice
import org.jetbrains.kotlin.course.culinary.models.food.SpiceType
import org.jetbrains.kotlin.course.culinary.models.storage.Shelf

data object ShelfImpl : Shelf {
    override fun getSpice(what: SpiceType): Spice = Spice(what)
}
