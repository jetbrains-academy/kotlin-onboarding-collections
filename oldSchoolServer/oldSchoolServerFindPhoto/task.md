In this task, you will become familiar with the built-in [`find`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/find.html) function. 
This function returns the _first_ element matching the given predicate, 
or `null` if no such element was found.

Implement the `findPhoto` function from the `GameFunctionsService` class.
This function takes an iterable collection of photo character names and 
also a colour name to be found in the collection.
You need to convert each element from the source collection to `PhotoCharacter` 
and find _only the first one_ with `backgroundColor` equal to the one passed.

You can use the already implemented functions `toColor` and `toPhotoCharacters` to implement the `findPhoto` function.

After implementing this task, you will be able to find photos by color.

<div class="hint" title="Click me to view the expected state of the application after completing this task">

![Current state](../../utils/src/main/resources/images/old/school/states/state_2.gif)

</div>

Note that only the first photo is highlighted, as this is how the Kotlin `find` function works.

If you have any difficulties, **hints will help you solve this task**.

----

### Hints

<div class="hint" title="Click me to learn about the 'with' function">

To implement the `findPhoto` function, you need to convert `colorStr` to `Color`. 
It is more efficient to do it only once and then to call the `filter` function, e.g.:
```kotlin
fun Iterable<String>.findPhoto(colorStr: String): PhotoCharacter? {
    val color = colorStr.toColor()
    return toPhotoCharacters().find { it.backgroundColor == color }
}
```

But in Kotlin, this code can be rewritten with the [`with`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/with.html) function, which allows you 
to avoid defining a new variable:
```kotlin
fun Iterable<String>.findPhoto(colorStr: String) = with(colorStr.toColor()) {
    toPhotoCharacters().find { it.backgroundColor == this }
}
```

This function allows you to use an additional _context_, e.g., in the code snippets below, 
we convert `colorStr` to `Color` only once and then we can use the produced `Color` _context_.
In the code example, we use `this` when we want to refer to the converted color.
</div>
