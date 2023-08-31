package org.jetbrains.kotlin.course.duck.shop.duck

enum class Duck(
    val customName: String? = null,
    val hasKotlinAttribute: Boolean = false,
    val accessories: List<Accessory> = emptyList(),
) {
    Alex(accessories = listOf(Accessory.Monocle, Accessory.Hat)),
    Daniel(hasKotlinAttribute = true, accessories = listOf(Accessory.Flag)),
    Dorian(accessories = listOf(Accessory.Crown)),
    Jack(accessories = listOf(Accessory.PirateEyePatch)),
    Kristian(accessories = listOf(Accessory.Tie)),
    Leo(accessories = listOf(Accessory.Glasses)),
    MrPink("Mr. Pink", accessories = listOf(Accessory.Medal)),
    Oliver,
    Piter(accessories = listOf(Accessory.Hat, Accessory.Pin)),
    Vanessa(hasKotlinAttribute = true, accessories = listOf(Accessory.TShirt))
    ;
}

enum class Accessory(val price: Int = 0) {
    Hat(75),
    Monocle(90),
    Flag(30),
    Crown(100),
    PirateEyePatch(15),
    Tie(25),
    Glasses(110),
    Medal(18),
    Pin(6),
    TShirt(45)
    ;
}

fun generateRandomDuck(): Duck = TODO("Not implemented yet")

fun Duck.getDescription() = this.customName ?: this.name
