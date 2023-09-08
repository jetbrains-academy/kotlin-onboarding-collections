package org.jetbrains.kotlin.course.duck.shop.mode

import org.jetbrains.kotlin.course.duck.shop.duck.Duck
import org.jetbrains.kotlin.course.duck.shop.duck.generateRandomDuck
import org.jetbrains.kotlin.course.duck.shop.utils.MAX_NUMBER_OF_DUCKS
import org.springframework.stereotype.Service

@Service
class GameModeService {
    // You could reuse the same approach with the shuffled function here,
    // but this way demonstrates different approaches to work with collections
    fun generateListOfDucks() = List(MAX_NUMBER_OF_DUCKS) { generateRandomDuck() }

    fun generateSetOfDucks() = Duck.entries.toList().shuffled().take(MAX_NUMBER_OF_DUCKS).toSet()

    fun generateMapOfDucks(): Map<Duck, String> = TODO("Not implemented yet")
}
