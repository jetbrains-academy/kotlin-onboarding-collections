package org.jetbrains.kotlin.course.old.school.functions

import org.jetbrains.kotlin.course.old.school.photo.Color
import org.jetbrains.kotlin.course.old.school.photo.PhotoCharacter
import org.springframework.stereotype.Service

@Service
class GameFunctionsService {
    fun getAllPossibleColors() = Color.entries.map { it.name.lowercase() }

    private fun String.capitalize() = replaceFirstChar { it.titlecase() }

    private fun String.toColor() = Color.valueOf(capitalize())

    private fun Iterable<String>.toPhotoCharacters() =
        map { name -> PhotoCharacter.valueOf(name.capitalize()) }

    fun Iterable<String>.findPhoto(colorStr: String) = with(colorStr.toColor()) {
        toPhotoCharacters().find { it.backgroundColor == this }
    }
    fun Iterable<String>.groupPhotosByColor() = toPhotoCharacters()
        .groupBy { it.backgroundColor }.map { it.value }.flatten()

    fun Iterable<String>.groupPhotosByHairAndHat(): List<PhotoCharacter> = TODO("Not implemented yet")
}
