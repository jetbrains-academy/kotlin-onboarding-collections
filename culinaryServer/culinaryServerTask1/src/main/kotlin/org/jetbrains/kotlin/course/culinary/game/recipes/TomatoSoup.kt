@file:Suppress("ForbiddenComment")

package org.jetbrains.kotlin.course.culinary.game.recipes

import org.jetbrains.kotlin.course.culinary.game.fridge
import org.jetbrains.kotlin.course.culinary.game.kitchen
import org.jetbrains.kotlin.course.culinary.game.pot
import org.jetbrains.kotlin.course.culinary.game.shelf
import org.jetbrains.kotlin.course.culinary.models.food.SpiceType
import org.jetbrains.kotlin.course.culinary.models.food.Vegetable
import org.jetbrains.kotlin.course.culinary.models.food.VegetableType
import kotlin.random.Random

internal const val NUMBER_OF_TOMATOES = 3

// TODO: add tests
// task#1
fun getTomatoesForSoup(): List<Vegetable> =
    List(NUMBER_OF_TOMATOES) { fridge.getVegetable(what = VegetableType.Tomato) }

// TODO: add tests
// task#1
fun prepareTomatoes(tomatoes: List<Vegetable>) {
    tomatoes
        .onEach { kitchen.put(it) }
        .map { kitchen.cut(it) }
        .forEach { pot.put(kitchen.take(it)) }
}

fun cookSoup() {
    pot.simmer()
}

// task#2
fun generateSpices(): Sequence<SpiceType> = generateSequence { SpiceType.entries.random() }

// task#2
fun addSpecies(spices: Sequence<SpiceType>) {
    val howMuchToAdd = Random.nextInt(1, 10)
    spices
        .map { shelf.getSpice(it) }
        .map { pot.put(it) }
        .take(howMuchToAdd)
        .toList() // terminate
}
