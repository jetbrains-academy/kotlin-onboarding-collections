package org.jetbrains.kotlin.course.culinary.game

import org.jetbrains.kotlin.course.culinary.game.recipes.*
import org.jetbrains.kotlin.course.culinary.models.food.CutVegetable
import org.jetbrains.kotlin.course.culinary.models.food.FruitType
import org.springframework.stereotype.Service

@Service
class CookingService {
    companion object {
        const val NUM_VEGETABLES_FOR_SALAD = 5
    }

    // task#1
    fun cookTomatoSoup() {
        val tomatoes = getTomatoesForSoup()
        prepareTomatoes(tomatoes)
        cookSoup()
    }

    // task#2
    fun cookWithSpices() {
        val spices = generateSpices()
        addSpices(spices)
        pot.simmer()
    }

    // task#3
    fun cookSaladAsList() {
        val listOfVegetables = fridge.getAllVegetables().filter { it.isFresh }
        val cutVegetables = listOfVegetables.map { kitchen.put(it) }
            .filter { kitchen.checkFresh(it) }
            .map { kitchen.cut(it) }
            .take(NUM_VEGETABLES_FOR_SALAD)
            .map { kitchen.take(it) }
        mixVegetablesForSalad(cutVegetables)
    }

    // task#3
    fun cookSaladAsSequence() {
        val listOfVegetables = fridge.getAllVegetables().filter { it.isFresh }.asSequence()
        val cutVegetables = listOfVegetables.map { kitchen.put(it) }
            .filter { kitchen.checkFresh(it) }
            .map { kitchen.cut(it) }
            .take(NUM_VEGETABLES_FOR_SALAD)
            .map { kitchen.take(it) }
            .toList()
        mixVegetablesForSalad(cutVegetables)
    }

    private fun mixVegetablesForSalad(cutVegetables: List<CutVegetable>) {
        cutVegetables.groupBy { it.type }
            .forEach { (type, cuts) -> saladBowl.add(type, cuts) }
        saladBowl.mix()
    }

    // task#4
    fun cookSmoothie(){
        FruitType.entries.map { type -> fridge.getBasketOf(type) }
            .onEach { basket -> kitchen.put(basket) }
            .flatMap { basket -> List(basket.capacity) { kitchen.takeFromBasket(basket) } }
            .sortedBy { it.type.sugarContent }
            .forEach { blender.add(it) }
        blender.blend()
    }
}