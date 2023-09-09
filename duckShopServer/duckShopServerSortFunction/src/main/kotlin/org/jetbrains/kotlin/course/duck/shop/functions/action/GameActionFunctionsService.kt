package org.jetbrains.kotlin.course.duck.shop.functions.action

import org.jetbrains.kotlin.course.duck.shop.duck.Duck
import org.springframework.stereotype.Service

@Service
class GameActionFunctionsService {

    fun List<Duck>.shuffleDucks(): List<Duck> = shuffled()

    fun List<Duck>.sortDucks() = sortedByDescending { d ->
        d.accessories.sumOf {
            val coefficient = if (d.hasKotlinAttribute) 100 else 1
            it.price * coefficient
        }
    }

    fun Collection<Duck>.deleteDucksWithoutKotlinStuff() = filter { it.hasKotlinAttribute }

    fun Map<Duck, String>.deleteDucksWithoutKotlinStuff() = filterKeys { it.hasKotlinAttribute }

    fun Collection<Duck>.divideDucksIntoKotlinAndNonKotlin() = partition { it.hasKotlinAttribute }
}
