package org.jetbrains.kotlin.course.duck.shop.functions.change

import org.jetbrains.kotlin.course.duck.shop.duck.Duck
import org.jetbrains.kotlin.course.duck.shop.duck.addOneDuck
import org.jetbrains.kotlin.course.duck.shop.duck.generateRandomDuck
import org.jetbrains.kotlin.course.duck.shop.duck.getDescription
import org.jetbrains.kotlin.course.duck.shop.utils.MAX_NUMBER_OF_DUCKS
import org.springframework.stereotype.Service

@Service
class GameChangeFunctionsService  {
    fun List<Duck>.addRandomDuck(): Duck = TODO("Not implemented yet")
    fun Set<Duck>.addRandomDuck(): Duck = TODO("Not implemented yet")

    fun MutableMap<Duck, String>.addRandomDuck(): Pair<Duck, String> = TODO("Not implemented yet")

    fun List<Duck>.removeRandomDuck(): List<Duck> = TODO("Not implemented yet")

    fun Set<Duck>.removeRandomDuck(): Set<Duck> = TODO("Not implemented yet")

    fun Map<Duck, String>.removeRandomDuck(): Map<Duck, String> = TODO("Not implemented yet")
}
