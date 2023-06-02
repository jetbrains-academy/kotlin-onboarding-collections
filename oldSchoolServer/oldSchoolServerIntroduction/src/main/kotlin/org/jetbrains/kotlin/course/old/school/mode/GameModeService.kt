package org.jetbrains.kotlin.course.old.school.mode

import org.jetbrains.kotlin.course.old.school.photo.PhotoCharacter
import org.springframework.stereotype.Service

@Service
class GameModeService {
    companion object {
        const val MAX_NUMBER_OF_PHOTOS = 4
    }
    private fun generateRandomCharacter() = PhotoCharacter.values().random()

    fun generateListOfCharacters() = List(MAX_NUMBER_OF_PHOTOS) { generateRandomCharacter() }

    fun generateSetOfCharacters(): Set<PhotoCharacter> {
        val characters = mutableSetOf<PhotoCharacter>()
        do {
            characters.add(generateRandomCharacter())
        } while (characters.size < MAX_NUMBER_OF_PHOTOS)
        return characters
    }

    fun generateMapOfCharacters(): Map<PhotoCharacter, String> {
        val characters = mutableMapOf<PhotoCharacter, String>()
        do {
            val character = generateRandomCharacter()
            characters.putIfAbsent(character, character.name)
        } while (characters.size < MAX_NUMBER_OF_PHOTOS)
        return characters
    }
}
