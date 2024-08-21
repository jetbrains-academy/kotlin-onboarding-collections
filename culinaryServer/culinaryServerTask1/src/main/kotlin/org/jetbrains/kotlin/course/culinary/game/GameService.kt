package org.jetbrains.kotlin.course.culinary.game

import org.jetbrains.kotlin.course.culinary.game.recipes.*
import org.jetbrains.kotlin.course.culinary.models.food.FruitType
import org.springframework.stereotype.Service

@Service
class CookingService {
    // task#1
    fun cookTomatoSoup() {
        val tomatoes = getTomatoesForSoup()
        prepareTomatoes(tomatoes)
        cookSoup()
    }

    // task#2
    fun cookWithSpices() {
        val spices = generateSpices()
        addSpecies(spices)
        pot.simmer()
    }

    // task#3
    fun cookSalad() {
        fridge.getAllVegetables()
            .map { kitchen.put(it) }
            .filter { kitchen.checkFresh(it) }
            .map { kitchen.cut(it) }
            .take(5)
            .map { kitchen.take(it) }
            .groupBy { it.type }
            .forEach { (type, cuts) -> saladBowl.add(type, cuts) }
        saladBowl.mix()
    }

    // task#4
    fun cookSmoothie(){
        val fruits = listOf(FruitType.Citrus, FruitType.Berry)
        fruits.map { type -> fridge.getBasketOf(type) }
            .onEach { basket -> kitchen.put(basket) }
            .flatMap { basket -> List(basket.capacity) { kitchen.takeFromBasket(basket) } }
            .distinctBy { it.type }
            .sortedBy { it.type.sugarContent }
            .forEach { blender.add(it) }
        blender.blend()
    }
}