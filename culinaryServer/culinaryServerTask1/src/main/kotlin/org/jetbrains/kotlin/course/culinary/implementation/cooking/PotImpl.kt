package org.jetbrains.kotlin.course.culinary.implementation.cooking

import org.jetbrains.kotlin.course.culinary.game.actions
import org.jetbrains.kotlin.course.culinary.converters.buildAction
import org.jetbrains.kotlin.course.culinary.models.action.Action
import org.jetbrains.kotlin.course.culinary.models.action.ActionType
import org.jetbrains.kotlin.course.culinary.models.cooking.Pot
import org.jetbrains.kotlin.course.culinary.models.food.*

data object PotImpl : Pot {
    val filling: MutableList<Ingredient> = mutableListOf()
    var simmering: Boolean = false

    override fun <T: Ingredient> put(ingredient: T) {
        filling.add(ingredient)
        actions.add(buildAction(ActionType.PUT_IN_POT, ingredient))
    }

    override fun put(vegetable: CutVegetable) {
        filling.add(vegetable)
    }

    private fun checkIfManySpices() = filling.filter{ it is Spice }
        .groupingBy{ it }
        .eachCount()
        .filter{ (_, n) -> n > 2 }
        .isNotEmpty()

    private fun checkIfAllVegetablesFresh() = filling.filter{ it is Vegetable }.all{ (it as Vegetable).isFresh }

    // task#2
    override fun doesTastePerfect(): Boolean = checkIfManySpices() && checkIfAllVegetablesFresh()

    override fun simmer() {
        check(!simmering) { "You are already simmering" }
        simmering = true
        actions += Action(ActionType.SIMMER)
    }

    override fun reset() {
        filling.clear()
        simmering = false
    }
}
