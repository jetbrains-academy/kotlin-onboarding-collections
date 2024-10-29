package org.jetbrains.kotlin.course.culinary.implementation.cooking

import org.jetbrains.kotlin.course.culinary.game.actions
import org.jetbrains.kotlin.course.culinary.converters.buildAction
import org.jetbrains.kotlin.course.culinary.models.action.Action
import org.jetbrains.kotlin.course.culinary.models.action.ActionType
import org.jetbrains.kotlin.course.culinary.models.cooking.Blender
import org.jetbrains.kotlin.course.culinary.models.food.Fruit

internal data object BlenderImpl : Blender {
    val filling: MutableList<Fruit> = mutableListOf()
    var blending = false

    override fun blend() {
        check(!blending) { "You are already blending." }
        blending = true
        actions += Action(ActionType.BLEND)
    }

    override fun add(fruit: Fruit) {
        filling.add(fruit)
        actions += buildAction(ActionType.ADD_TO_BLENDER, fruit)
    }

    override fun reset() {
        filling.clear()
        blending = false
    }
}
