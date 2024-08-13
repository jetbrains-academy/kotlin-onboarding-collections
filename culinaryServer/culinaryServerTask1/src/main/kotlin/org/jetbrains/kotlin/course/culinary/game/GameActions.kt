package org.jetbrains.kotlin.course.culinary.game

import culinary.JsAction
import org.jetbrains.kotlin.course.culinary.implementation.KitchenImpl
import org.jetbrains.kotlin.course.culinary.implementation.cooking.BlenderImpl
import org.jetbrains.kotlin.course.culinary.implementation.cooking.PotImpl
import org.jetbrains.kotlin.course.culinary.implementation.cooking.SaladBowlImpl
import org.jetbrains.kotlin.course.culinary.implementation.storage.FridgeImpl

internal val actions: MutableList<JsAction> = ArrayList()

internal fun clearKitchen() {
    FridgeImpl.refill()
    PotImpl.reset()
    SaladBowlImpl.reset()
    BlenderImpl.reset()
    KitchenImpl.reset()
    actions.clear()
}
