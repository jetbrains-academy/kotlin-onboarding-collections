package org.jetbrains.kotlin.course.culinary.functions

import culinary.JsAction
import org.jetbrains.kotlin.course.culinary.*
import org.jetbrains.kotlin.course.culinary.actions
import org.springframework.http.StreamingHttpOutputMessage
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/functions/")
class CookingFunction(val service: CookingService) {
    @CrossOrigin
    @GetMapping("/cooking")
    fun recipe(@RequestBody body: StreamingHttpOutputMessage.Body): List<JsAction> {
        service.performCooking()
        clearKitchen()
        return actions
    }

    @CrossOrigin
    @GetMapping("/test-task1")
    fun task1(@RequestBody body: StreamingHttpOutputMessage.Body): List<JsAction> {
        val vegetables = List(3) { fridge.getVegetable(what = VegetableType.Tomato) }
        vegetables
            .onEach { counter.put(it) }
            .map { counter.cut(it) }
            .forEach { pot.put(counter.take(it)) }
        pot.simmer()
        clearKitchen()
        return actions
    }

    @CrossOrigin
    @GetMapping("/test-task2")
    fun task2(@RequestBody body: StreamingHttpOutputMessage.Body): List<JsAction> {
        val spices = generateSequence { SpiceType.entries.random() }
        spices
            .map { shelf.getSpice(it) }
            .map { pot.put(it) }
            .takeWhile { !pot.doesTastePerfect() }
            .toList() // terminate
        pot.simmer()
        clearKitchen()
        return actions
    }

    @CrossOrigin
    @GetMapping("/test-task3")
    fun task3(@RequestBody body: StreamingHttpOutputMessage.Body): List<JsAction> {
        fridge.getAllVegetables()
            .map { counter.put(it) }
            .filter { counter.checkFresh(it) }
            .map { counter.cut(it) }
            .take(5)
            .map { counter.take(it) }
            .groupBy { it.type }
            .forEach { (type, cuts) -> saladBowl.add(type, cuts) }
        saladBowl.mix()
        clearKitchen()
        return actions
    }

    @CrossOrigin
    @GetMapping("/test-task4")
    fun task4(@RequestBody body: StreamingHttpOutputMessage.Body): List<JsAction> {
        val fruits = listOf(FruitType.Citrus, FruitType.Berry)
        fruits.map { type -> fridge.getBasketOf(type) }
            .onEach { basket -> counter.put(basket) }
            .flatMap { basket -> List(basket.capacity) { counter.takeFromBasket(basket) } }
            .distinctBy { it.type }
            .sortedBy { it.type.sugarContent }
            .forEach { blender.add(it) }
        blender.blend()
        clearKitchen()
        return actions
    }
}


fun main() {
    val fruits = listOf(FruitType.Citrus, FruitType.Berry)
    fruits.map { type -> fridge.getBasketOf(type) }
        .onEach { basket -> counter.put(basket) }
        .flatMap { basket -> List(basket.capacity) { counter.takeFromBasket(basket) } }
        .distinctBy { it.type }
        .sortedBy { it.type.sugarContent }
        .forEach { blender.add(it) }
    blender.blend()
    println(actions.joinToString("\n"))
}