package org.jetbrains.kotlin.course.tamagotchi.game

import org.jetbrains.kotlin.course.tamagotchi.models.Command
import org.jetbrains.kotlin.course.tamagotchi.models.Mode
import org.springframework.stereotype.Service
import java.util.ArrayDeque

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

    fun getCommand(mode: Mode): Command? {
        if (commands.isEmpty()) {
            return null
        }
        return when(mode) {
            Mode.Queue -> commands.removeFirst()
            Mode.Stack -> commands.removeLast()
        }
    }
}
