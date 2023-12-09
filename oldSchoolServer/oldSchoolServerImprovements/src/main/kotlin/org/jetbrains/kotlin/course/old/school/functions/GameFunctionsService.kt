package org.jetbrains.kotlin.course.old.school.functions

import org.jetbrains.kotlin.course.old.school.photo.Accessory
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
        .groupBy { it.backgroundColor }.flatMap { it.value }

    fun Iterable<String>.groupPhotosByHairAndHat() = toPhotoCharacters()
        .groupBy { it.hairType }
        .mapValues { it.value.groupBy { character ->
            character.accessories?.contains(Accessory.Hat) ?: false
        }
        }.values.flatMap { it.values }
        .flatten()
}
