package org.jetbrains.kotlin.course.duck.shop.duck

enum class Duck(
    val customName: String? = null
) {
    Kristian,
    Alex,
    MrPink("Mr. Pink"),
    Piter,
    Dorian,
    Vanessa
    ;
}
