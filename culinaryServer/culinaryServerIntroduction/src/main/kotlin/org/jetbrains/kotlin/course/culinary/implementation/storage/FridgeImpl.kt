@file:Suppress("ForbiddenComment")

package org.jetbrains.kotlin.course.culinary.implementation.storage

import org.jetbrains.kotlin.course.culinary.models.food.FruitType
import org.jetbrains.kotlin.course.culinary.models.food.Vegetable
import org.jetbrains.kotlin.course.culinary.models.food.VegetableType
import org.jetbrains.kotlin.course.culinary.models.storage.Basket
import org.jetbrains.kotlin.course.culinary.models.storage.Fridge
import kotlin.random.Random

data object FridgeImpl : Fridge {
    private const val RANDOM_VEGETABLES_NUMBER = 7
    private const val RANDOM_FRESH_VEGETABLES_NUMBER = 3

    val vegetables: MutableList<Vegetable> = mutableListOf()

    private fun generateRandomVegetables(): List<Vegetable> {
        TODO("Not implemented yet")
    }

    fun refill() {
        TODO("Not implemented yet")
    }

    override fun getVegetable(what: VegetableType): Vegetable {
        val vegetable = checkNotNull(vegetables.find { it.type == what && it.isFresh }) { "Fresh vegetable $what not found." }
        vegetables.remove(vegetable)
        return vegetable
    }

    override fun getAllVegetables(): Collection<Vegetable> {
        check(vegetables.isNotEmpty()) { "No more vegetables left in the fridge" }
        val allVegs = vegetables.shuffled()
        vegetables.clear()
        return allVegs
    }

    override fun getBasketOf(type: FruitType): Basket = Basket(type, Random.nextInt(3, 5))
}
