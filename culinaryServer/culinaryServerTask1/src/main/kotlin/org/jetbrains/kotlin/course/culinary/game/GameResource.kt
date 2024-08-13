package org.jetbrains.kotlin.course.culinary.game

import culinary.JsAction
import org.jetbrains.kotlin.course.culinary.models.food.FruitType
import org.jetbrains.kotlin.course.culinary.models.food.SpiceType
import org.jetbrains.kotlin.course.culinary.models.food.VegetableType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/functions/")
class CookingFunction(val service: CookingService) {
    @CrossOrigin
    @GetMapping("/test-task1")
    fun task1(): List<JsAction> {
        service.cookTomatoSoup()
        clearKitchen()
        return actions
    }

    @CrossOrigin
    @GetMapping("/test-task2")
    fun task2(): List<JsAction> {
        service.cookWithSpices()
        clearKitchen()
        return actions
    }

    @CrossOrigin
    @GetMapping("/test-task3")
    fun task3(): List<JsAction> {
        service.cookSalad()
        clearKitchen()
        return actions
    }

    @CrossOrigin
    @GetMapping("/test-task4")
    fun task4(): List<JsAction> {
        service.cookSmoothie()
        clearKitchen()
        return actions
    }
}
