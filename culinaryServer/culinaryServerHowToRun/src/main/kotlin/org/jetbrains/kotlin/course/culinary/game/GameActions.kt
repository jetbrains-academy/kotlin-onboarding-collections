package org.jetbrains.kotlin.course.culinary.game

import org.jetbrains.kotlin.course.culinary.implementation.KitchenImpl
import org.jetbrains.kotlin.course.culinary.implementation.cooking.BlenderImpl
import org.jetbrains.kotlin.course.culinary.implementation.cooking.PotImpl
import org.jetbrains.kotlin.course.culinary.implementation.cooking.SaladBowlImpl
import org.jetbrains.kotlin.course.culinary.implementation.storage.FridgeImpl
import org.jetbrains.kotlin.course.culinary.models.action.Action

internal val actions: MutableList<Action> = ArrayList()

internal fun clearKitchen() {
    PotImpl.reset()
    SaladBowlImpl.reset()
    BlenderImpl.reset()
    KitchenImpl.reset()
}

fun clearActions() {
    actions.clear()
}
