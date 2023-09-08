package org.jetbrains.kotlin.course.duck.shop.utils

import org.jetbrains.kotlin.course.duck.shop.duck.Duck

enum class GameMode(val key: String) {
    List("list"),
    Set("set"),
    Map("map"),
    ;
}

data class AppBody(
    val ducks: List<Duck>,
    val mode: GameMode
)
