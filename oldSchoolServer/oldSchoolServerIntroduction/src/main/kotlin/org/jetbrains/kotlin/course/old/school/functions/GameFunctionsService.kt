package org.jetbrains.kotlin.course.old.school.functions

import org.jetbrains.kotlin.course.old.school.photo.Color
import org.jetbrains.kotlin.course.old.school.photo.PhotoCharacter
import org.springframework.stereotype.Service

@Service
class GameFunctionsService {
    fun getAllPossibleColors() = Color.values().map { it.name.lowercase() }

    fun Iterable<String>.findPhoto(colorStr: String): PhotoCharacter? {
        val color = Color.valueOf(colorStr.replaceFirstChar { it.titlecase() })
        return map { name -> PhotoCharacter.valueOf(name.replaceFirstChar { it.titlecase() }) }
            .find { it.backgroundColor == color }
    }
}
