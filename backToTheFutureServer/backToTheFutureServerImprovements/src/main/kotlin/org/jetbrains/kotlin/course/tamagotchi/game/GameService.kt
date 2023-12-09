package org.jetbrains.kotlin.course.tamagotchi.game

import org.jetbrains.kotlin.course.tamagotchi.models.Command
import org.jetbrains.kotlin.course.tamagotchi.models.Mode
import org.springframework.stereotype.Service

@Service
class GameService {
    companion object {
        private const val MAX_CAPACITY = 16
    }
    val commands = ArrayDeque<Command>()

    fun addCommand(command: Command) = if (commands.size < MAX_CAPACITY) {
        commands.add(command)
    } else {
        false
    }

    fun getCommand(mode: Mode): Command? = when(mode) {
        Mode.Queue -> commands.removeFirstOrNull()
        Mode.Stack -> commands.removeLastOrNull()
    }
}
