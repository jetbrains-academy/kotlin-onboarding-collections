package org.jetbrains.kotlin.course.old.school.functions

import org.jetbrains.kotlin.course.old.school.photo.Color
import org.jetbrains.kotlin.course.old.school.photo.PhotoCharacter
import org.springframework.stereotype.Service

@Service
class GameFunctionsService {
    fun getAllPossibleColors(): List<String> = TODO("Not implemented yet")

    private fun String.toColor(): Color = TODO("Not implemented yet")

    private fun Iterable<String>.toPhotoCharacters(): List<PhotoCharacter> = TODO("Not implemented yet")

    fun Iterable<String>.findPhoto(colorStr: String): PhotoCharacter? = TODO("Not implemented yet")

    fun Iterable<String>.groupPhotosByColor(): List<PhotoCharacter> = TODO("Not implemented yet")

    fun Iterable<String>.groupPhotosByHairAndHat(): List<PhotoCharacter> = TODO("Not implemented yet")
}
