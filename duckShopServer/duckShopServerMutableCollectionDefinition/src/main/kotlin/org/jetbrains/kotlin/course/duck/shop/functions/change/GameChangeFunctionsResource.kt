package org.jetbrains.kotlin.course.duck.shop.functions.change

import duck.shop.JsDuck
import org.jetbrains.kotlin.course.duck.shop.duck.getDescription
import org.jetbrains.kotlin.course.duck.shop.functions.common.Body
import org.jetbrains.kotlin.course.duck.shop.utils.GameMode
import org.jetbrains.kotlin.course.duck.shop.utils.toAppBody
import org.jetbrains.kotlin.course.duck.shop.utils.toJsDuck
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/functions/")
class GameChangeFunctionsResource(val service: GameChangeFunctionsService) {
    @CrossOrigin
    @PostMapping("/add")
    fun addDuck(@RequestBody body: Body): JsDuck = with(service) {
        val appBody = body.toAppBody()
        val newDuck = when(appBody.mode) {
            GameMode.List -> appBody.ducks.toMutableList().addRandomDuck()
            GameMode.Set -> appBody.ducks.toMutableSet().addRandomDuck()
            GameMode.Map -> appBody.ducks.associateWith { it.getDescription() }.toMutableMap().addRandomDuck().first
        }
        newDuck.toJsDuck(appBody.mode)
    }

    @CrossOrigin
    @PostMapping("/remove")
    fun removeDuck(@RequestBody body: Body): List<JsDuck> = with(service) {
        val appBody = body.toAppBody()
        val ducks = when(appBody.mode) {
            GameMode.List -> appBody.ducks.removeRandomDuck()
            GameMode.Set -> appBody.ducks.toSet().removeRandomDuck()
            GameMode.Map -> appBody.ducks.associateWith { it.getDescription() }.removeRandomDuck().keys
        }
        ducks.map { it.toJsDuck(appBody.mode) }
    }
}
