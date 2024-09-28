package org.jetbrains.kotlin.course.culinary.game

import org.jetbrains.kotlin.course.culinary.converters.toItemType
import org.jetbrains.kotlin.course.culinary.game.recipes.NUMBER_OF_TOMATOES
import org.jetbrains.kotlin.course.culinary.game.recipes.NUM_VEGETABLES_FOR_SALAD
import org.jetbrains.kotlin.course.culinary.implementation.storage.FridgeImpl
import org.jetbrains.kotlin.course.culinary.models.ItemType
import org.jetbrains.kotlin.course.culinary.models.Task
import org.jetbrains.kotlin.course.culinary.models.action.Action
import org.jetbrains.kotlin.course.culinary.models.food.VegetableType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/functions/")
class CookingFunction(val service: CookingService) {
    @CrossOrigin
    @GetMapping("/refill-fridge")
    fun refillFridge(): List<ItemType> {
        FridgeImpl.refill()
        return FridgeImpl.vegetables.map{ it.toItemType() }
    }

    @CrossOrigin
    @GetMapping("/tomato-soup")
    fun tomatoSoup(): List<Action> {
        clearActions()
        if (FridgeImpl.vegetables.count{ it.type == VegetableType.Tomato && it.isFresh } < NUMBER_OF_TOMATOES) {
            // Show an error
            return emptyList()
        }

        service.cookTomatoSoup()
        clearKitchen()
        return actions
    }

    @CrossOrigin
    @GetMapping("/soup-spices")
    fun soupSpices(): List<Action> {
        clearActions()
        service.cookWithSpices()
        clearKitchen()
        return actions
    }

    @CrossOrigin
    @GetMapping("/check-soup")
    fun checkSoup(): Boolean = pot.doesTastePerfect()

    @CrossOrigin
    @GetMapping("/salad-list")
    fun cookSaladAsList(): List<Action> {
        clearActions()
        if (FridgeImpl.vegetables.count{ it.isFresh } < NUM_VEGETABLES_FOR_SALAD) {
            // Show an error
            return emptyList()
        }

        service.cookSaladAsList()
        clearKitchen()
        return actions
    }

    @CrossOrigin
    @GetMapping("/salad-sequence")
    fun cookSaladAsSequence(): List<Action> {
        clearActions()
        if (FridgeImpl.vegetables.count{ it.isFresh } < NUM_VEGETABLES_FOR_SALAD) {
            // Show an error
            return emptyList()
        }

        service.cookSaladAsSequence()
        clearKitchen()
        return actions
    }

    @CrossOrigin
    @GetMapping("/smoothie")
    fun cookSmoothieAsList(): List<Action> {
        clearActions()
        service.cookSmoothie()
        clearKitchen()
        return actions
    }

    @CrossOrigin
    @GetMapping("/current-task")
    fun getCurrentTask(): String = Task.SMOOTHIE.toString()
}
