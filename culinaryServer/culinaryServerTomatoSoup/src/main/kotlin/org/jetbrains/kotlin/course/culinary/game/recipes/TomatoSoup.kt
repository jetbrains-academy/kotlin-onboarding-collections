@file:Suppress("ForbiddenComment")

package org.jetbrains.kotlin.course.culinary.game.recipes

import org.jetbrains.kotlin.course.culinary.game.fridge
import org.jetbrains.kotlin.course.culinary.game.kitchen
import org.jetbrains.kotlin.course.culinary.game.pot
import org.jetbrains.kotlin.course.culinary.models.food.SpiceType
import org.jetbrains.kotlin.course.culinary.models.food.Vegetable
import org.jetbrains.kotlin.course.culinary.models.food.VegetableType

const val NUMBER_OF_TOMATOES = 3

fun getTomatoesForSoup(): List<Vegetable> =
    List(NUMBER_OF_TOMATOES) { fridge.getVegetable(what = VegetableType.Tomato) }

fun prepareTomatoes(tomatoes: List<Vegetable>) {
    tomatoes
        .onEach { kitchen.put(it) }
        .map { kitchen.cut(it) }
        .forEach { pot.put(kitchen.take(it)) }
}

fun generateSpices(): Sequence<SpiceType> = TODO("Not implemented yet")

fun addSpices(spices: Sequence<SpiceType>) {
    TODO("Not implemented yet")
}
