package org.jetbrains.kotlin.course.culinary.functions

import org.jetbrains.kotlin.course.culinary.*
import org.springframework.stereotype.Service

@Service
class CookingService {
    fun performCooking() {
        fun task1() {
            val vegetables = List(3) { fridge.getVegetable(what = VegetableType.Tomato) }
            vegetables
                .onEach { counter.put(it) }
                .map { counter.cut(it) }
                .forEach { pot.put(counter.take(it)) }

            pot.simmer()
        }

        fun task2() {
            val spices = generateSequence { SpiceType.entries.random() }
            spices
                .map { shelf.getSpice(it) }
                .map { pot.put(it) }
                .takeWhile { !pot.doesTastePerfect() }
                .toList() // terminate

            pot.simmer()
        }

        fun task3() {
            fridge.getAllVegetables()
                .map { counter.put(it) }
                .filter { counter.checkFresh(it) }
                .map { counter.cut(it) }
                .take(5)
                .map { counter.take(it) }
                .groupBy { it.type }
                .forEach { (type, cuts) -> saladBowl.add(type, cuts) }

            saladBowl.mix()
        }

        fun task4() {
            val fruits = listOf(FruitType.Citrus, FruitType.Berry)

            fruits.map { type -> fridge.getBasketOf(type) }
                .onEach { basket -> counter.put(basket) }
                .flatMap { basket -> List(basket.capacity) { counter.takeFromBasket(basket) } }
                .distinctBy { it.type }
                .sortedBy { it.type.sugarContent }
                .forEach { blender.add(it) }

            blender.blend()
        }

        task4()
        println(actions.joinToString("\n"))
    }
}