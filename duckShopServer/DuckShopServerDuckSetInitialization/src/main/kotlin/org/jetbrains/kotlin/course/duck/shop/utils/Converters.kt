package org.jetbrains.kotlin.course.duck.shop.utils

import duck.shop.JsDuck
import org.jetbrains.kotlin.course.duck.shop.duck.Duck
import org.jetbrains.kotlin.course.duck.shop.functions.common.Body

fun String.toDuck() = Duck.entries.find { it.name == this || it.customName == this } ?: error("Can not find the duck $this")

fun String.toGameMode() = GameMode.valueOf(this)

fun Body.toAppBody() = AppBody(
    ducks = this.ducks.map { it.toDuck() },
    mode = this.mode.toGameMode()
)

fun Duck.getJsDescription(mode: GameMode) = when(mode) {
    GameMode.Map -> this.customName ?: this.name
    else -> null
}

fun Duck.toJsDuck(mode: GameMode) = JsDuck(this.customName ?: this.name, hasKotlinAttribute = this.hasKotlinAttribute, description = this.getJsDescription(mode))
