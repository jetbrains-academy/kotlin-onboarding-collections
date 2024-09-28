package org.jetbrains.kotlin.course.culinary.game.recipes

import org.jetbrains.kotlin.course.culinary.game.fridge
import org.jetbrains.kotlin.course.culinary.game.kitchen
import org.jetbrains.kotlin.course.culinary.game.saladBowl
import org.jetbrains.kotlin.course.culinary.models.food.CutVegetable
import org.jetbrains.kotlin.course.culinary.models.food.Vegetable

const val NUM_VEGETABLES_FOR_SALAD = 5

fun getFreshVegetables(): List<Vegetable> = fridge.getAllVegetables().filter { it.isFresh }

fun List<Vegetable>.cut(): List<CutVegetable> = map { kitchen.put(it) }
    .map { kitchen.cut(it) }
    .take(NUM_VEGETABLES_FOR_SALAD)
    .map { kitchen.take(it) }

fun mixVegetablesForSalad(cutVegetables: List<CutVegetable>) {
    cutVegetables.groupBy { it.type }
        .forEach { (type, cuts) -> saladBowl.add(type, cuts) }
    saladBowl.mix()
}
