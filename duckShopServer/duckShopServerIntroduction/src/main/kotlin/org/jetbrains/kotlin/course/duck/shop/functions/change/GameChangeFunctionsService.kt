package org.jetbrains.kotlin.course.duck.shop.functions.change

import org.jetbrains.kotlin.course.duck.shop.duck.Duck
import org.jetbrains.kotlin.course.duck.shop.duck.addOneDuck
import org.jetbrains.kotlin.course.duck.shop.duck.generateRandomDuck
import org.jetbrains.kotlin.course.duck.shop.duck.getDescription
import org.jetbrains.kotlin.course.duck.shop.utils.MAX_NUMBER_OF_DUCKS
import org.springframework.stereotype.Service

@Service
class GameChangeFunctionsService  {
    fun List<Duck>.addRandomDuck(): Duck {
        require(this.size < MAX_NUMBER_OF_DUCKS) { "The collection of ducks already contains $MAX_NUMBER_OF_DUCKS ducks!" }
        return generateRandomDuck().also { this.toMutableList().add(it) }
    }

    fun Set<Duck>.addRandomDuck(): Duck {
        require(this.size < MAX_NUMBER_OF_DUCKS) { "The collection of ducks already contains $MAX_NUMBER_OF_DUCKS ducks!" }
        return this.toMutableSet().addOneDuck()
    }

    fun MutableMap<Duck, String>.addRandomDuck(): Pair<Duck, String> {
        require(this.size < MAX_NUMBER_OF_DUCKS) { "The collection of ducks already contains $MAX_NUMBER_OF_DUCKS ducks!" }
        return this.toMutableMap().addOneDuck()
    }

    fun List<Duck>.removeRandomDuck(): List<Duck> {
        require(this.isNotEmpty()) { "The collection of ducks is empty!" }
        return this.toMutableList().also {
            it.removeAt(indices.random())
        }
    }

    fun Set<Duck>.removeRandomDuck(): Set<Duck> {
        require(this.isNotEmpty()) { "The collection of ducks is empty!" }
        return this.toMutableSet().also {
            it.remove(it.random())
        }
    }

    fun Map<Duck, String>.removeRandomDuck(): Map<Duck, String> {
        require(this.isNotEmpty()) { "The collection of ducks is empty!" }
        val duck = this.keys.random()
        return this.toMutableMap().also {
            it.remove(duck)
        }
    }
}