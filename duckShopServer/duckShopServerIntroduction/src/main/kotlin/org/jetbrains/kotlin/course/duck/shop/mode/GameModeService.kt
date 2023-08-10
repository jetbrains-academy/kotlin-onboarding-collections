package org.jetbrains.kotlin.course.duck.shop.mode

import org.jetbrains.kotlin.course.duck.shop.duck.Duck
import org.springframework.stereotype.Service

@Service
class GameModeService {
    companion object {
        const val MAX_NUMBER_OF_DUCKS = 6
    }
    private fun generateRandomDuck() = Duck.values().random()

    fun generateListOfDucks() = List(MAX_NUMBER_OF_DUCKS) { generateRandomDuck() }

    fun generateSetOfDucks(): Set<Duck> {
        val ducks = mutableSetOf<Duck>()
        do {
            ducks.add(generateRandomDuck())
        } while (ducks.size < MAX_NUMBER_OF_DUCKS)
        return ducks
    }

    fun generateMapOfDucks(): Map<Duck, String> {
        val ducks = mutableMapOf<Duck, String>()
        do {
            val duck = generateRandomDuck()
            ducks.putIfAbsent(duck, duck.customName ?: duck.name)
        } while (ducks.size < MAX_NUMBER_OF_DUCKS)
        return ducks
    }
}
