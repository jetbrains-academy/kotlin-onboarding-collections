package org.jetbrains.kotlin.course.culinary.game

import org.jetbrains.kotlin.course.culinary.game.recipes.*
import org.jetbrains.kotlin.course.culinary.models.food.CutVegetable
import org.jetbrains.kotlin.course.culinary.models.food.Vegetable
import org.springframework.stereotype.Service

@Service
class CookingService {
    fun cookTomatoSoup() {
        val tomatoes = getTomatoesForSoup()
        prepareTomatoes(tomatoes)
        pot.simmer()
    }

    fun cookWithSpices() {
        val spices = generateSpices()
        addSpices(spices)
        pot.simmer()
    }

    fun cookSaladAsList() {
        mixVegetablesForSalad(getFreshVegetables().cut())
    }

    fun cookSaladAsSequence() {
        mixVegetablesForSalad(getFreshVegetables().asSequence().cut())
    }

    private fun Sequence<Vegetable>.cut(): List<CutVegetable> = map { kitchen.put(it) }
        .map { kitchen.cut(it) }
        .take(NUM_VEGETABLES_FOR_SALAD)
        .map { kitchen.take(it) }
        .toList()

    fun cookSmoothie(){
        getFruitsForSmoothie().cookSmoothie()
    }
}
