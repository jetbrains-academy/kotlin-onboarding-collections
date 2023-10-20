@file:Suppress("UnusedParameter")

package org.jetbrains.kotlin.course.tamagotchi.game

import org.jetbrains.kotlin.course.tamagotchi.models.Command
import org.jetbrains.kotlin.course.tamagotchi.models.Mode
import org.springframework.web.bind.annotation.*
import java.util.ArrayDeque

@RestController
@RequestMapping("/api/command/")
class GameResource(val service: GameService) {
    private fun String.toMode() = Mode.entries.find{ it.name.lowercase() == this.lowercase() }
        ?: error("Cannot create mode for $this")

    private fun String.removeQuotes() = replace("\"", "")
    @CrossOrigin
    @PostMapping("/get")
    fun getCommand(@RequestBody mode: String): Command? = service.getCommand(mode.removeQuotes().toMode())

    private fun Int.toCommand() = when(this) {
        0 -> Command.Eat
        1 -> Command.Sleep
        2 -> Command.Clean
        3 -> Command.Play
        else -> error("Cannot create command for $this")
    }

    @CrossOrigin
    @PostMapping("/add")
    fun addCommand(@RequestBody command: Int): Boolean = service.addCommand(command.toCommand())

    @CrossOrigin
    @GetMapping("/all")
    fun getAllCommands() = service.commands
}
