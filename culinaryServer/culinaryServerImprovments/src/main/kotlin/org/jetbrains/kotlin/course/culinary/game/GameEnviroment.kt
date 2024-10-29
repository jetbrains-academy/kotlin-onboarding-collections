package org.jetbrains.kotlin.course.culinary.game

import org.jetbrains.kotlin.course.culinary.implementation.KitchenImpl
import org.jetbrains.kotlin.course.culinary.implementation.cooking.BlenderImpl
import org.jetbrains.kotlin.course.culinary.implementation.cooking.PotImpl
import org.jetbrains.kotlin.course.culinary.implementation.cooking.SaladBowlImpl
import org.jetbrains.kotlin.course.culinary.implementation.storage.FridgeImpl
import org.jetbrains.kotlin.course.culinary.implementation.storage.ShelfImpl
import org.jetbrains.kotlin.course.culinary.models.Kitchen
import org.jetbrains.kotlin.course.culinary.models.cooking.Blender
import org.jetbrains.kotlin.course.culinary.models.cooking.Pot
import org.jetbrains.kotlin.course.culinary.models.cooking.SaladBowl
import org.jetbrains.kotlin.course.culinary.models.storage.Fridge
import org.jetbrains.kotlin.course.culinary.models.storage.Shelf

val kitchen: Kitchen = KitchenImpl

val fridge: Fridge = FridgeImpl
val shelf: Shelf = ShelfImpl
val pot: Pot = PotImpl

val blender: Blender = BlenderImpl
val saladBowl: SaladBowl = SaladBowlImpl
