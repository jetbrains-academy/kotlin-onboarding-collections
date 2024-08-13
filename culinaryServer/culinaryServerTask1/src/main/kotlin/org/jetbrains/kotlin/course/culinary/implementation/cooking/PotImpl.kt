package org.jetbrains.kotlin.course.culinary.implementation.cooking

import culinary.JsAction
import culinary.JsActionType
import org.jetbrains.kotlin.course.culinary.game.actions
import org.jetbrains.kotlin.course.culinary.converters.buildJsAction
import org.jetbrains.kotlin.course.culinary.models.cooking.Pot
import org.jetbrains.kotlin.course.culinary.models.food.*

data object PotImpl : Pot {
    val filling: MutableList<Ingredient> = mutableListOf()
    var simmering = false

    override fun <T: Ingredient> put(ingredient: T) {
        if (filling.add(ingredient)) {
            actions.add(buildJsAction(JsActionType.PUT_IN_POT, ingredient))
        }
        else {
            error("You are trying to put the same $ingredient in the pot twice")
        }
    }

    override fun put(vegetable: CutVegetable) {
        filling.add(vegetable)
    }

    private fun checkIfManySpices() = filling.filter{ it is Spice }
        .groupingBy{ it }
        .eachCount()
        .filter{ (s, n) -> n > 2 }
        .isNotEmpty()

    private fun checkIfAllVegetablesFresh() = filling.filter{ it is Vegetable }.all{ (it as Vegetable).isFresh }

    override fun doesTastePerfect(): Boolean = checkIfManySpices() && checkIfAllVegetablesFresh()

    override fun simmer() {
        check(!simmering) { "You are already simmering" }
        simmering = true
        actions += JsAction(JsActionType.SIMMER)
    }

    override fun reset() {
        filling.clear()
        simmering = false
    }
}
