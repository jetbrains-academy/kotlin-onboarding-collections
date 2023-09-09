In this task you need to implement a function to be able to
divide ducks in the Duck Shop into two categories - with any Kotlin stuff and without it - and reorder them according to this condition.

### Task

Implement the `divideDucksIntoKotlinAndNonKotlin` function from the `GameActionFunctionsService` class in
the `org.jetbrains.kotlin.course.duck.shop.functions.action` package.
This function should divide ducks into two groups - with any Kotlin attribute (the field `hasKotlinAttribute` is `true`) and without - and returns these two collections.

We have only one function, since the function `fun Collection<Duck>.divideDucksIntoKotlinAndNonKotlin()` works for both collections - for `List` and for `Set`.
The map collection does not have this function.

After implementing this task the `Partition` button in `list` and `set` modes becomes alive:

![Current state](../../utils/src/main/resources/images/duck/shop/states/state_7.gif)

If you have any difficulties, **hints will help you solve this task**.

----

### Hints

<div class="hint" title="How can I divide a collection by condition?">

You can do it in several ways, but the easiest one is to use the [`partition`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/partition.html) built-in function.
It accepts a predicate (condition) to divide a collection into two parts according to this condition:

```kotlin
val listOfNumbers = listOf(1, 2, 3, 4, 5, 6)
println(listOfNumbers.partition { it % 2 == 0 }) // Returns a pair of two lists: [2, 4, 6], [1, 3, 5]
```
</div>
