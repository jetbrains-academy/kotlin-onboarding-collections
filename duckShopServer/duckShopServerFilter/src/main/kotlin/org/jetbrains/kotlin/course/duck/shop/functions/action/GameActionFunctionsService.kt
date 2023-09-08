package org.jetbrains.kotlin.course.duck.shop.functions.action

import org.jetbrains.kotlin.course.duck.shop.duck.Duck
import org.springframework.stereotype.Service

@Service
class GameActionFunctionsService {

    fun List<Duck>.shuffleDucks(): List<Duck> = TODO("Not implemented yet")

    fun List<Duck>.sortDucks(): List<Duck> = TODO("Not implemented yet")

    fun Collection<Duck>.deleteDucksWithoutKotlinStuff() = filter { it.hasKotlinAttribute }

    fun Map<Duck, String>.deleteDucksWithoutKotlinStuff() = filterKeys { it.hasKotlinAttribute }

    fun Collection<Duck>.divideDucksIntoKotlinAndNonKotlin(): Pair<Collection<Duck>, Collection<Duck>> = TODO("Not implemented yet")
}
