@file:Suppress("UnusedParameter")

package org.jetbrains.kotlin.course.old.school.functions

import org.springframework.web.bind.annotation.*

// We can not use a typealias here because the Spring framework can not parse it
class Characters : ArrayList<String>()
class Body (
    val names: Characters,
    val color: String
)

@RestController
@RequestMapping("/api/functions/")
class GameFunctionsResource(val service: GameFunctionsService) {
    @CrossOrigin
    @GetMapping("/colors")
    fun getAllPossibleColors(): List<String> = service.getAllPossibleColors()

    @CrossOrigin
    @PostMapping("/find")
    fun findPhoto(@RequestBody body: Body): String? = with(service) {
        body.names.findPhoto(body.color)?.name?.lowercase()
    }
    @CrossOrigin
    @PostMapping("/groupByByColor")
    fun groupByPhotosByColor(@RequestBody names: List<String>): List<String> = with(service) {
        names.groupPhotosByColor().map { it.name }
    }
    @CrossOrigin
    @PostMapping("/groupByPhotosByHairAndHat")
    fun groupByPhotosByHairAndHat(@RequestBody names: List<String>): List<String> = with(service) {
        names.groupPhotosByHairAndHat().map { it.name }
    }
}
