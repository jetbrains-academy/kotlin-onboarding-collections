package org.jetbrains.kotlin.course.duck.shop.functions.change

import org.jetbrains.kotlin.course.duck.shop.duck.Duck
import org.jetbrains.kotlin.course.duck.shop.duck.generateRandomDuck
import org.jetbrains.kotlin.course.duck.shop.duck.getDescription
import org.jetbrains.kotlin.course.duck.shop.utils.MAX_NUMBER_OF_DUCKS
import org.springframework.stereotype.Service

@Service
class GameChangeFunctionsService  {
    fun List<Duck>.addRandomDuck() = generateRandomDuck().also { this.toMutableList().add(it) }

    fun MutableSet<Duck>.addOneDuck(): Duck {
        var wasAdded: Boolean
        var duck: Duck
        do {
            duck = generateRandomDuck()
            wasAdded = this.add(duck)
        } while (!wasAdded)
        return duck
    }

    fun Set<Duck>.addRandomDuck() = this.toMutableSet().addOneDuck()

    fun MutableMap<Duck, String>.addOneDuck(): Pair<Duck, String> {
        var duck: Duck
        var description: String
        val oldSize = this.size
        do {
            duck = generateRandomDuck()
            description = duck.getDescription()
            this.putIfAbsent(duck, description)
        } while (this.size - 1 != oldSize)
        return Pair(duck, description)
    }

    fun MutableMap<Duck, String>.addRandomDuck() = this.toMutableMap().addOneDuck()
    fun List<Duck>.removeRandomDuck() = toMutableList().also {
        it.removeAt(indices.random())
    }

    fun Set<Duck>.removeRandomDuck() = toMutableSet().also {
        it.remove(it.random())
    }

    fun Map<Duck, String>.removeRandomDuck() = toMutableMap().also {
        it.remove(keys.random())
    }
}
