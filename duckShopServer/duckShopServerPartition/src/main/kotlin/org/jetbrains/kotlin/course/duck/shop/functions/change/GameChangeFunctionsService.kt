package org.jetbrains.kotlin.course.duck.shop.functions.change

import org.jetbrains.kotlin.course.duck.shop.duck.Duck
import org.jetbrains.kotlin.course.duck.shop.duck.generateRandomDuck
import org.jetbrains.kotlin.course.duck.shop.duck.getDescription
import org.jetbrains.kotlin.course.duck.shop.utils.MAX_NUMBER_OF_DUCKS
import org.springframework.stereotype.Service

@Service
class GameChangeFunctionsService  {
    fun List<Duck>.addRandomDuck() = generateRandomDuck().also { toMutableList().add(it) }

    fun Collection<Duck>.getNewRandomDuck() = Duck.values().toList().minus(toSet()).shuffled().random()

    fun Set<Duck>.addRandomDuck() = getNewRandomDuck().also {
        toMutableSet().add(it)
    }

    fun MutableMap<Duck, String>.addRandomDuck(): Pair<Duck, String> {
        val duck = keys.getNewRandomDuck()
        return Pair(duck, duck.getDescription()).also { (duck, d) ->
            toMutableMap()[duck] = d
        }
    }

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
