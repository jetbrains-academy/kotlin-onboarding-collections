package org.jetbrains.kotlin.course.culinary.implementation.cooking

import org.jetbrains.kotlin.course.culinary.game.actions
import org.jetbrains.kotlin.course.culinary.converters.buildAction
import org.jetbrains.kotlin.course.culinary.models.action.Action
import org.jetbrains.kotlin.course.culinary.models.action.ActionType
import org.jetbrains.kotlin.course.culinary.models.cooking.SaladBowl
import org.jetbrains.kotlin.course.culinary.models.food.CutVegetable
import org.jetbrains.kotlin.course.culinary.models.food.Ingredient
import org.jetbrains.kotlin.course.culinary.models.food.VegetableType

data object SaladBowlImpl : SaladBowl {
    val filling: MutableList<Ingredient> = mutableListOf()
    var mixing: Boolean = false

    override fun add(type: VegetableType, cuts: List<CutVegetable>) {
        requireNotNull(cuts.all { it.type == type }) {
            "One should put the vegetables to the salad bowl grouping them by type"
        }
        for (it in cuts) {
            filling.add(it)
            actions += buildAction(ActionType.ADD_TO_SALAD, it)
        }
    }

    override fun mix() {
        check(!mixing) { "You are already mixing" }
        mixing = true
        actions += Action(ActionType.MIX_SALAD)
    }

    override fun reset() {
        filling.clear()
        mixing = false
    }
}
