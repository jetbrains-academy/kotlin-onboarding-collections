package org.jetbrains.kotlin.course.duck.shop.mode

import org.jetbrains.kotlin.course.duck.shop.duck.Duck
import org.jetbrains.kotlin.course.duck.shop.duck.addOneDuck
import org.jetbrains.kotlin.course.duck.shop.duck.generateRandomDuck
import org.jetbrains.kotlin.course.duck.shop.utils.MAX_NUMBER_OF_DUCKS
import org.springframework.stereotype.Service

@Service
class GameModeService {
    fun generateListOfDucks() = List(MAX_NUMBER_OF_DUCKS) { generateRandomDuck() }

    fun generateSetOfDucks(): Set<Duck> {
        val ducks = mutableSetOf<Duck>()
        repeat(MAX_NUMBER_OF_DUCKS) {
            ducks.addOneDuck()
        }
        return ducks
    }

    fun generateMapOfDucks(): Map<Duck, String> {
        val ducks = mutableMapOf<Duck, String>()
        repeat(MAX_NUMBER_OF_DUCKS) {
            ducks.addOneDuck()
        }
        return ducks
    }
}
