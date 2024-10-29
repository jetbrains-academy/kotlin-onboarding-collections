package org.jetbrains.kotlin.course.culinary.game.recipes

import org.jetbrains.kotlin.course.culinary.game.blender
import org.jetbrains.kotlin.course.culinary.game.fridge
import org.jetbrains.kotlin.course.culinary.game.kitchen
import org.jetbrains.kotlin.course.culinary.models.food.Fruit
import org.jetbrains.kotlin.course.culinary.models.food.FruitType

fun getFruitsForSmoothie(): List<Fruit> = FruitType.entries.map { type -> fridge.getBasketOf(type) }
    .onEach { basket -> kitchen.put(basket) }
    .flatMap { basket -> List(basket.capacity) { kitchen.takeFromBasket(basket) } }
    .sortedBy { it.type.sugarContent }

fun List<Fruit>.cookSmoothie() {
    forEach { blender.add(it) }
    blender.blend()
}
