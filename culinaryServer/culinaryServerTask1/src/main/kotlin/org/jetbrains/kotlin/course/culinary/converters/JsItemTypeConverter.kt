package org.jetbrains.kotlin.course.culinary.converters

import culinary.JsActionType
import culinary.JsItemType
import org.jetbrains.kotlin.course.culinary.models.food.*
import org.jetbrains.kotlin.course.culinary.models.storage.Basket

@Suppress("CyclomaticComplexMethod")
internal fun Ingredient.toJsItemType(): JsItemType = when (this) {
    is Vegetable -> {
        when (type) {
            VegetableType.Tomato -> if (isFresh)
                JsItemType.FRESH_TOMATO
            else
                JsItemType.ROT_TOMATO

            VegetableType.Cucumber -> if (isFresh)
                JsItemType.FRESH_CUCUMBER
            else
                JsItemType.ROT_CUCUMBER

            VegetableType.Carrot -> if (isFresh)
                JsItemType.FRESH_CARROT
            else
                JsItemType.ROT_CARROT
        }
    }

    is CutVegetable -> {
        when (type) {
            VegetableType.Tomato -> JsItemType.CUT_TOMATO
            VegetableType.Cucumber -> JsItemType.CUT_CUCUMBER
            VegetableType.Carrot -> JsItemType.CUT_CARROT
        }
    }

    is Basket -> when (type) {
        FruitType.Berry -> JsItemType.BERRY_BASKET
        FruitType.Citrus -> JsItemType.CITRUS_BASKET
    }

    is Fruit -> when (type) {
        FruitType.Berry -> JsItemType.BERRY
        FruitType.Citrus -> JsItemType.CITRUS
    }

    is Spice -> when (type) {
        SpiceType.Salt -> JsItemType.SALT
        SpiceType.Pepper -> JsItemType.PEPPER
        SpiceType.Oregano -> JsItemType.OREGANO
    }

    else -> error("Internal error! Unsupported ingredient $this")
}

internal fun buildJsAction(type: JsActionType, parameter: Ingredient) =
    culinary.JsAction(type, parameter.toJsItemType())
