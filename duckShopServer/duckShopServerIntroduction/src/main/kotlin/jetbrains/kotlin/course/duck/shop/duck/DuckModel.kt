package jetbrains.kotlin.course.duck.shop.duck

enum class DuckColor {
    Yellow,
    Green
    ;
}

enum class DuckAccessuare {
    Hat,
    TShirt,
    ;
}

// TODO: add more attributes
data class DuckModel(
    val color: DuckColor,
    val accessuares: Set<DuckAccessuare>
) {
    companion object {
        // TODO: generate random number of accessuares
        fun generateDuck() = DuckModel(
            color = DuckColor.values().random(),
            accessuares = DuckAccessuare.values().toMutableList().shuffled().take((DuckAccessuare.values().size)).toSet()
        )
    }
}