package org.jetbrains.kotlin.course.duck.shop.functions.action

import org.jetbrains.kotlin.course.duck.shop.duck.Duck
import org.springframework.stereotype.Service

@Service
class GameActionFunctionsService {

    fun List<Duck>.shuffleDucks(): List<Duck> = this.shuffled()

    fun List<Duck>.sortDucks(): List<Duck> = this.sortedByDescending { d ->
        d.accessories.sumOf {
            val coefficient = if (d.hasKotlinAttribute) 100 else 1
            it.price * coefficient
        }
    }

    fun Collection<Duck>.deleteDucksWithoutKotlinStuff() = this.filter { it.hasKotlinAttribute }

    fun Map<Duck, String>.deleteDucksWithoutKotlinStuff() = this.filter { it.key.hasKotlinAttribute }

    fun Collection<Duck>.divideDucksIntoKotlinAndNonKotlin() = this.partition { it.hasKotlinAttribute }
}
