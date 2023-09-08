package org.jetbrains.kotlin.course.duck.shop.mode

import org.jetbrains.kotlin.course.duck.shop.duck.Duck
import org.jetbrains.kotlin.course.duck.shop.duck.generateRandomDuck
import org.jetbrains.kotlin.course.duck.shop.duck.getDescription
import org.jetbrains.kotlin.course.duck.shop.utils.MAX_NUMBER_OF_DUCKS
import org.springframework.stereotype.Service

@Service
class GameModeService {
    // You could reuse the same approach with the shuffled function here,
    // but this way demonstrates different approaches to work with collections
    fun generateListOfDucks() = List(MAX_NUMBER_OF_DUCKS) { generateRandomDuck() }

    fun generateSetOfDucks() = getRandomDucks().toSet()

    // It is better to move common code into a separated function
    private fun getRandomDucks() = Duck.entries.toList().shuffled().take(MAX_NUMBER_OF_DUCKS)

    fun generateMapOfDucks() = getRandomDucks().associateWith { it.getDescription() }
}
