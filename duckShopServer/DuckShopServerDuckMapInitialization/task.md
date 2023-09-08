In this task you need to implement a
function to be able to initialize a map of random ducks in the Duck Shop.

### Task

Implement `generateMapOfDucks` function from the `GameModeService` class in
the `org.jetbrains.kotlin.course.duck.shop.mode` package.
This function should generate a new map with `MAX_NUMBER_OF_DUCKS` random ducks, 
as a value for each duck you need to use `getDescription` function from the `Duck.kt` file.

You can find the already defined variable `MAX_NUMBER_OF_DUCKS`
in the `GameUtil.kt` file in the `org.jetbrains.kotlin.course.duck.shop.utils` package.

After implementing this task the `Map` button becomes alive:

![Current state](../../utils/src/main/resources/images/duck/shop/states/state_3.gif)

If you have any difficulties, **hints will help you solve this task**.

----

### Hints

<div class="hint" title="How to create a map from a list?">

You can use the [`associateWith`]("https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/associate-with.html) built-in function to associate each item from list with a value and create a map:

```kotlin
val l = listOf("cat", "dog", "rabbit")
val m = l.associateWith { it.length } // The map {"cat": 3, "dog": 3, "rabbit": 6} will be created
```
</div>