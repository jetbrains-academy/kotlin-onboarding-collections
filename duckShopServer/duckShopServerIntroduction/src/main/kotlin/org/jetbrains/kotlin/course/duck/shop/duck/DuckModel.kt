@file:Suppress("ForbiddenComment")

package org.jetbrains.kotlin.course.duck.shop.duck

enum class DuckColor {
    Yellow,
    Green
    ;
}

enum class DuckAccessory {
    Hat,
    TShirt,
    ;
}

// TODO: add more attributes
data class DuckModel(
    val color: DuckColor,
    val accessories: Set<DuckAccessory>
) {
    companion object {
        // TODO: generate random number of accessuares
        fun generateDuck() = DuckModel(
            color = DuckColor.values().random(),
            accessories = DuckAccessory.values().toMutableList().shuffled().take((DuckAccessory.values().size)).toSet()
        )
    }
}
