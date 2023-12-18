The main goal of this task is to prepare everything to implement sorting and grouping functions.
Implement two functions from the `GameFunctionsService` class to complete the task:

1) the `toColor` function, which returns `Color` by its name: e.g., for the input `white`, it should return `Color.White`
2) the `toPhotoCharacters` function, which converts an _iterable_ collection of names, e.g., a list or a set, into a 
list of `PhotoCharacter`. You can find the already defined `PhotoCharacter` enum class in the `PhotoCharacter.kt` file.
For example, the list `[emily, david]` should be converted to the list `[PhotoCharacter.Emily, PhotoCharacter.David]`

If you have any difficulties, **hints will help you solve this task**.

----

### Hints

<div class="hint" title="Click me to learn possible approaches to get an enum entity by name">

We propose two main approaches to return a particular enum entity by its name:

1) Use the built-in function [`valueOf`](https://kotlinlang.org/docs/enum-classes.html#working-with-enum-constants), 
which throws an `IllegalArgumentException` if the specified name does not 
match any of the enum constants defined in the class:
```kotlin
enum class Color {
    White,
    Blue,
    ;
}

fun main() {
    println(Color.valueOf("White")) // Color.White
    
    println(Color.valueOf("white")) // ERROR
    println(Color.valueOf("Red")) // ERROR
}
```

2) Get all possible entries and find the necessary one by condition:
```kotlin
enum class Color {
    White,
    Blue,
    ;
}

fun main() {
    Color.entries.find { it.name == "White" }?.let{ println(it) } ?: error("Cannot find the color")
}
```
</div>

<div class="hint" title="Click me to learn how to capitalize a string">

To capitalize a string, you can use the built-in function [`replaceFirstChar`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/replace-first-char.html) 
with the [`titlecase`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/titlecase.html) function as an argument:
```kotlin
fun main() {
    println("white".replaceFirstChar { it.titlecase() }) // "White"
}
```
</div>
