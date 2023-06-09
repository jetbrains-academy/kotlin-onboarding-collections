package org.jetbrains.kotlin.course.old.school.photo

enum class PhotoCharacter(val backgroundColor: Color, val accessory: Accessory? = null) {
    Olivia(Color.Purple),
    Emily(Color.White, Accessory.Earrings),
    Emma(Color.Yellow),
    Charlotte(Color.Pink, Accessory.Earrings),
    Diana(Color.Orange, Accessory.Earrings),
    Alice(Color.Green),
    Helena(Color.Green, Accessory.Pen),
    Evie(Color.Purple, Accessory.Pen),

    Henry(Color.Pink, Accessory.Glasses),
    Daniel(Color.Purple),
    Noah(Color.Blue, Accessory.Glasses),
    David(Color.Gray, Accessory.Glasses),
    Leo(Color.Purple, Accessory.Glasses),
    Jamie(Color.Gray, Accessory.Glasses),
    Larry(Color.Orange, Accessory.Glasses),
    Thomas(Color.White),
    Oliver(Color.Blue),
    Edward(Color.White, Accessory.Glasses)
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
    Pen
    ;
}
