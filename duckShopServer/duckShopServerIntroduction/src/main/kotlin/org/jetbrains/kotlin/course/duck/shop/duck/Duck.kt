package org.jetbrains.kotlin.course.duck.shop.duck

enum class Duck(
    val customName: String? = null,
    val hasKotlinAttribute: Boolean = false,
) {
    Alex,
    Daniel(hasKotlinAttribute = true),
    Dorian,
    Jack,
    Kristian,
    Leo,
    MrPink("Mr. Pink"),
    Oliver,
    Piter,
    Vanessa(hasKotlinAttribute = true)
    ;
}
