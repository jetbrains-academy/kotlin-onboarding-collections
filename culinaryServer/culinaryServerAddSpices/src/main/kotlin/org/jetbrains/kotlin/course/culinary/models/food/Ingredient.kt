package org.jetbrains.kotlin.course.culinary.models.food

interface Ingredient

data class Vegetable internal constructor(val type: VegetableType, val isFresh: Boolean) : Ingredient

data class CutVegetable internal constructor(val type: VegetableType) : Ingredient

data class Fruit internal constructor(val type: FruitType) : Ingredient

data class Spice internal constructor(val type: SpiceType) : Ingredient
