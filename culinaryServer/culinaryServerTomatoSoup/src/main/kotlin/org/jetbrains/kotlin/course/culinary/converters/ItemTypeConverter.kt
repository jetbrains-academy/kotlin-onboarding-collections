package org.jetbrains.kotlin.course.culinary.converters

import org.jetbrains.kotlin.course.culinary.models.action.Action
import org.jetbrains.kotlin.course.culinary.models.action.ActionType
import org.jetbrains.kotlin.course.culinary.models.ItemType
import org.jetbrains.kotlin.course.culinary.models.food.*
import org.jetbrains.kotlin.course.culinary.models.storage.Basket

@Suppress("CyclomaticComplexMethod")
internal fun Ingredient.toItemType(): ItemType = when (this) {
    is Vegetable -> {
        when (type) {
            VegetableType.Tomato -> if (isFresh)
                ItemType.FRESH_TOMATO
            else
                ItemType.ROT_TOMATO

            VegetableType.Cucumber -> if (isFresh)
                ItemType.FRESH_CUCUMBER
            else
                ItemType.ROT_CUCUMBER

            VegetableType.Carrot -> if (isFresh)
                ItemType.FRESH_CARROT
            else
                ItemType.ROT_CARROT
        }
    }

    is CutVegetable -> {
        when (type) {
            VegetableType.Tomato -> ItemType.CUT_TOMATO
            VegetableType.Cucumber -> ItemType.CUT_CUCUMBER
            VegetableType.Carrot -> ItemType.CUT_CARROT
        }
    }

    is Basket -> when (type) {
        FruitType.Berry -> ItemType.BERRY_BASKET
        FruitType.Citrus -> ItemType.CITRUS_BASKET
    }

    is Fruit -> when (type) {
        FruitType.Berry -> ItemType.BERRY
        FruitType.Citrus -> ItemType.CITRUS
    }

    is Spice -> when (type) {
        SpiceType.Salt -> ItemType.SALT
        SpiceType.Pepper -> ItemType.PEPPER
        SpiceType.Oregano -> ItemType.OREGANO
    }

    else -> error("Internal error! Unsupported ingredient $this")
}

internal fun buildAction(type: ActionType, parameter: Ingredient) =
    Action(type, parameter.toItemType())
