It's time to practice with the `groupBy` function. 
Implement the `groupPhotosByColor` function from the `GameFunctionsService` class.
This function should group photos by background colour and then return 
the flattened list of grouped photos, e.g.:
```kotlin
fun main() {
    val photos = listOf(
        "olivia", // Purple
        "larry", // Orange
        "david", // Gray
        "daniel", // Purple
        "diana" // Orange
    )

    println(photos.groupPhotosByColor())
    // Groups by colors and then build a flatten 
    // list with the values for each color: 
    // - Purple: Olivia, Daniel
    // - Orange: Larry, Diana
    // - Gray: David
    //
    // So, the final flatted output is:
    // [Olivia, Daniel, Larry, Diana, David]
}
```

You can use the already implemented function `toPhotoCharacters` to implement the `groupPhotosByColor` function.

After implementing this task, you will be able to group photos by colour and reorder their on the album:

![Current state](../../utils/src/main/resources/images/old/school/states/state_3.gif)

If you have any difficulties, **hints will help you solve this task**.

----

### Hints

<div class="hint" title="How do I flatten a list of lists?">

The easiest way to do this is to use the built-in [`flatten`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/flatten.html) function:
```kotlin
fun main() {
    val photos = listOf(
        listOf("olivia", "daniel"),
        listOf("larry", "diana"),
        listOf("david"),
    )

    println(photos.flatten()) // [olivia, daniel, larry, diana, david]
}
```
</div>