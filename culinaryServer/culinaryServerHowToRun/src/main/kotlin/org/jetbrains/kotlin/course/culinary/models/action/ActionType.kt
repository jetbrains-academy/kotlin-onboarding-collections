package org.jetbrains.kotlin.course.culinary.models.action

enum class ActionType {
    SHOW_ON_COUNTER, // baskets, fruits, vegetables
    PUT_IN_POT, // cut vegetables
    SIMMER, // null
    ADD_TO_SALAD, // vegetables
    MIX_SALAD, // null
    BLEND, // null
    ADD_TO_BLENDER, // fruits
    REMOVE_FROM_COUNTER, // baskets, fruits, vegetables
    CUT_ON_COUNTER, // Cut a fruit or a vegetable
}
