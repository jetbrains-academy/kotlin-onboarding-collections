@file:OptIn(ExperimentalJsExport::class)

package culinary

import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

@JsExport
enum class JsItemType {
    CITRUS_BASKET,
    BERRY_BASKET,

    ROT_TOMATO,
    FRESH_TOMATO,
    CUT_TOMATO,

    ROT_CUCUMBER,
    FRESH_CUCUMBER,
    CUT_CUCUMBER,

    ROT_CARROT,
    FRESH_CARROT,
    CUT_CARROT,

    BERRY,
    CITRUS,

    SALT,
    PEPPER,
    OREGANO;
}

@JsExport
data class JsAction(val type: JsActionType, val parameter: JsItemType? = null)

@JsExport
enum class JsActionType {
    SHOW_ON_COUNTER, // baskets, fruits, vegetables
    PUT_IN_POT, // cut vegetables
    SIMMER, // null
    ADD_TO_SALAD, // vegetables
    MIX_SALAD, // null
    BLEND, // null
    ADD_TO_BLENDER, // fruits
    REMOVE_FROM_COUNTER, // baskets, fruits, vegetables
}
