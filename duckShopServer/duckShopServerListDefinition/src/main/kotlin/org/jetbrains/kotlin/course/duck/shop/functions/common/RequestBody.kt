package org.jetbrains.kotlin.course.duck.shop.functions.common

// We can not use a typealias here because the Spring framework can not parse it
class DuckNames : ArrayList<String>()
class Body (
    val ducks: DuckNames,
    val mode: String
)
