package org.jetbrains.kotlin.course.duck.shop.functions.action

import duck.shop.JsDuck
import org.jetbrains.kotlin.course.duck.shop.duck.getDescription
import org.jetbrains.kotlin.course.duck.shop.functions.common.Body
import org.jetbrains.kotlin.course.duck.shop.utils.GameMode
import org.jetbrains.kotlin.course.duck.shop.utils.toAppBody
import org.jetbrains.kotlin.course.duck.shop.utils.toJsDuck
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/functions/")
class GameActionFunctionsResource(val service: GameActionFunctionsService) {
    @CrossOrigin
    @PostMapping("/shuffle")
    fun shuffleDucks(@RequestBody body: Body): List<JsDuck> = with(service) {
        val appBody = body.toAppBody()
        appBody.ducks.shuffleDucks().map { it.toJsDuck(appBody.mode) }
    }

    @CrossOrigin
    @PostMapping("/sort")
    fun sortDucks(@RequestBody body: Body): List<JsDuck> = with(service) {
        val appBody = body.toAppBody()
        appBody.ducks.sortDucks().map { it.toJsDuck(appBody.mode) }
    }

    @CrossOrigin
    @PostMapping("/filter")
    fun filterDucks(@RequestBody body: Body): List<JsDuck> = with(service) {
        val appBody = body.toAppBody()
        val ducks = when(appBody.mode) {
            GameMode.List, GameMode.Set -> appBody.ducks.deleteDucksWithoutKotlinStuff()
            GameMode.Map -> appBody.ducks.associateWith { it.getDescription() }.deleteDucksWithoutKotlinStuff().keys
        }
        ducks.map { it.toJsDuck(appBody.mode) }
    }

    @CrossOrigin
    @PostMapping("/partition")
    fun partitionDucks(@RequestBody body: Body): List<List<JsDuck>> = with(service) {
        val appBody = body.toAppBody()
        val (withKotlin, withoutKotlin) = appBody.ducks.divideDucksIntoKotlinAndNonKotlin()
        listOf(withKotlin, withoutKotlin).map { it.map { d -> d.toJsDuck(appBody.mode)  } }
    }
}
