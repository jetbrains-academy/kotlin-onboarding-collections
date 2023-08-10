package org.jetbrains.kotlin.course.duck.shop.mode

import duck.shop.JsDuck
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
        service.generateListOfDucks().map { JsDuck(it.customName ?: it.name, hasKotlinAttribute = it.hasKotlinAttribute) }

    @CrossOrigin
    @GetMapping("/set")
    fun generateSetOfCharacters(): List<JsDuck> = service.generateSetOfDucks()
        .map { JsDuck(it.customName ?: it.name, hasKotlinAttribute = it.hasKotlinAttribute) }

    @CrossOrigin
    @GetMapping("/map")
    fun generateMapOfCharacters(): List<JsDuck> = service.generateMapOfDucks()
        .map { JsDuck(it.key.name, it.value, hasKotlinAttribute = it.key.hasKotlinAttribute) }
}
