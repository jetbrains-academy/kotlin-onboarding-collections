package org.jetbrains.kotlin.course.culinary.game

import org.jetbrains.kotlin.course.culinary.converters.toItemType
import org.jetbrains.kotlin.course.culinary.game.recipes.NUMBER_OF_TOMATOES
import org.jetbrains.kotlin.course.culinary.implementation.storage.FridgeImpl
import org.jetbrains.kotlin.course.culinary.models.ItemType
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
        service.cookWithSpices()
        clearKitchen()
        return actions
    }

    @CrossOrigin
    @GetMapping("/check-soup")
    fun checkSoup(): Boolean = pot.doesTastePerfect()

    @CrossOrigin
    @GetMapping("/test-task3")
    fun task3(): List<Action> {
        service.cookSalad()
        clearKitchen()
        return actions
    }

    @CrossOrigin
    @GetMapping("/test-task4")
    fun task4(): List<Action> {
        service.cookSmoothie()
        clearKitchen()
        return actions
    }
}
