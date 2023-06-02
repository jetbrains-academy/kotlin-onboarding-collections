package org.jetbrains.kotlin.course.old.school.mode

import old.school.JsPhoto
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/mode/")
class CardResource(val service: GameModeService) {
    @CrossOrigin
    @GetMapping("/list")
    fun generateListOfCharacters(): List<JsPhoto> = service.generateListOfCharacters().map { JsPhoto(it.name) }

    @CrossOrigin
    @GetMapping("/set")
    fun generateSetOfCharacters(): List<JsPhoto> = service.generateSetOfCharacters().map { JsPhoto(it.name) }

    @CrossOrigin
    @GetMapping("/map")
    fun generateMapOfCharacters(): List<JsPhoto> = service.generateMapOfCharacters().map { JsPhoto(it.key.name, it.value) }
}
