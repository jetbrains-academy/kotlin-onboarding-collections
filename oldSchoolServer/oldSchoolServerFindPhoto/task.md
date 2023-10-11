In this task, you will become familiar with the built-in [`find`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/find.html) function. 
This function returns the _first_ element matching the given predicate, 
or `null` if no such element was found.

Implement the `findPhoto` function from the `GameFunctionsService` class.
This function takes an iterable collection of photo character names and 
also takes a colour name to be found in the collection.
You need to convert each element from the source collection to `PhotoCharacter` 
and find _only the first one_ with `backgroundColor` equal to the one passed.

You can use the already implemented functions `toColor` and `toPhotoCharacters` to implement the `findPhoto` function.

After implementing this task, you will be able to find photos by color:

![Current state](../../utils/src/main/resources/images/old/school/states/state_2.gif)

Note that only the first photo is highlighted, as this is how the Kotlin `find' function works.
