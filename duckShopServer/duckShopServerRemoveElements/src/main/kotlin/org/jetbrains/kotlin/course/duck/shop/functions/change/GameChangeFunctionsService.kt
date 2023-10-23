package org.jetbrains.kotlin.course.duck.shop.functions.change

import org.jetbrains.kotlin.course.duck.shop.duck.Duck
import org.springframework.stereotype.Service

@Service
class GameChangeFunctionsService  {
    fun MutableList<Duck>.addRandomDuck(): Duck = TODO("Not implemented yet")

    fun MutableSet<Duck>.addRandomDuck(): Duck = TODO("Not implemented yet")

    fun MutableMap<Duck, String>.addRandomDuck(): Pair<Duck, String> = TODO("Not implemented yet")

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
