package org.jetbrains.kotlin.course.old.school.mode

import org.jetbrains.kotlin.course.old.school.photo.PhotoCharacter
import org.springframework.stereotype.Service

@Service
class GameModeService {
    companion object {
        const val MAX_NUMBER_OF_PHOTOS = 12
    }
    private fun generateRandomCharacter() = PhotoCharacter.entries.random()

    fun generateListOfCharacters() = List(MAX_NUMBER_OF_PHOTOS) { generateRandomCharacter() }

    // It is better to move common code into a separated function
    private fun getRandomCharacters() = PhotoCharacter.entries.shuffled().take(MAX_NUMBER_OF_PHOTOS)

    fun generateSetOfCharacters() = getRandomCharacters().toSet()

    fun generateMapOfCharacters() = getRandomCharacters().associateWith { it.name }
}
