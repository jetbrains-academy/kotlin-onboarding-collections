package org.jetbrains.kotlin.course.old.school.photo

enum class PhotoCharacter(
    val backgroundColor: Color,
    val hairType: HairType,
    val accessories: List<Accessory>? = null
) {
    Olivia(Color.Purple, HairType.Dark),
    Emily(Color.White, HairType.Dark, listOf(Accessory.Earrings, Accessory.Watch)),
    Emma(Color.Yellow, HairType.Light),
    Charlotte(Color.Pink, HairType.Dark, listOf(Accessory.Earrings, Accessory.Watch)),
    Diana(Color.Orange, HairType.Light, listOf(Accessory.Earrings, Accessory.Watch)),
    Alice(Color.Green, HairType.Light),
    Helena(Color.Green, HairType.Light, listOf(Accessory.Pen)),
    Evie(Color.Purple, HairType.Light, listOf(Accessory.Pen)),

    Henry(Color.Pink, HairType.Dark, listOf(Accessory.Glasses)),
    Daniel(Color.Purple, HairType.Dark, listOf(Accessory.Hat)),
    Noah(Color.Blue, HairType.Dark, listOf(Accessory.Glasses, Accessory.Hat)),
    David(Color.Gray, HairType.Dark, listOf(Accessory.Glasses, Accessory.Hat)),
    Leo(Color.Purple, HairType.Dark, listOf(Accessory.Glasses, Accessory.Hat)),
    Jamie(Color.Gray, HairType.Light, listOf(Accessory.Glasses)),
    Larry(Color.Orange, HairType.Light, listOf(Accessory.Glasses)),
    Thomas(Color.White, HairType.Light, listOf(Accessory.Hat)),
    Oliver(Color.Blue, HairType.Light, listOf(Accessory.Hat)),
    Edward(Color.White, HairType.Dark, listOf(Accessory.Glasses, Accessory.Hat))
    ;
}

enum class HairType {
    Dark,
    Light
    ;
}

enum class Color {
    White,
    Blue,
    Orange,
    Yellow,
    Pink,
    Purple,
    Green,
    Gray
    ;
}

enum class Accessory {
    Glasses,
    Earrings,
    Watch,
    Pen,
    Hat
    ;
}
