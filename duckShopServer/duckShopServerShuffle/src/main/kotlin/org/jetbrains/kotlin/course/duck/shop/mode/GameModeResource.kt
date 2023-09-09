package org.jetbrains.kotlin.course.duck.shop.mode

import duck.shop.JsDuck
import org.jetbrains.kotlin.course.duck.shop.utils.GameMode
import org.jetbrains.kotlin.course.duck.shop.utils.toJsDuck
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/mode/")
class CardResource(val service: GameModeService) {
    @CrossOrigin
    @GetMapping("/list")
    fun generateListOfCharacters(): List<JsDuck> =
        service.generateListOfDucks().map { it.toJsDuck(GameMode.List) }

    @CrossOrigin
    @GetMapping("/set")
    fun generateSetOfCharacters(): List<JsDuck> = service.generateSetOfDucks()
        .map { it.toJsDuck(GameMode.Set) }

    @CrossOrigin
    @GetMapping("/map")
    fun generateMapOfCharacters(): List<JsDuck> = service.generateMapOfDucks()
        .map { it.key.toJsDuck(GameMode.Map) }
}
