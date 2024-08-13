package org.jetbrains.kotlin.course.culinary.implementation.cooking

import culinary.JsAction
import culinary.JsActionType
import org.jetbrains.kotlin.course.culinary.game.actions
import org.jetbrains.kotlin.course.culinary.converters.buildJsAction
import org.jetbrains.kotlin.course.culinary.models.cooking.Blender
import org.jetbrains.kotlin.course.culinary.models.food.Fruit

internal data object BlenderImpl : Blender {
    val filling: MutableList<Fruit> = mutableListOf()
    var blending = false

    override fun blend() {
        check(!blending) { "You are already blending." }
        blending = true
        actions += JsAction(JsActionType.BLEND)
    }

    override fun add(fruit: Fruit) {
        filling.add(fruit)
        actions += buildJsAction(JsActionType.ADD_TO_BLENDER, fruit)
    }

    override fun reset() {
        filling.clear()
        blending = false
    }
}
