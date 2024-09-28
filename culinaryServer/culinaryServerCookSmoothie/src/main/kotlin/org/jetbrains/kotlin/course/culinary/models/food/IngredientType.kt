package org.jetbrains.kotlin.course.culinary.models.food

interface IngredientType

// Can be spoiled, raw or cut
enum class VegetableType : IngredientType {
    Tomato,
    Cucumber,
    Carrot;
}

enum class FruitType(val sugarContent: Int) : IngredientType {
    Berry(sugarContent = 20),
    Citrus(sugarContent = 10);
}

enum class SpiceType : IngredientType {
    Salt,
    Pepper,
    Oregano;
}
