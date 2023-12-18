In this task, you need to implement a function to be able to
sort ducks in the Duck Shop according to the cost of their accessories.
This step is the final one to finish this application.

### Task

Implement the `sortDucks` function from the `GameActionFunctionsService` class in
the `org.jetbrains.kotlin.course.duck.shop.functions.action` package.
This function should sort ducks in the list in descending order according to the cost of their accessories.
To calculate the duck's cost, you need to summarize the cost of all its accessories. 
Please use an extra coefficient `100` for each accessory if the duck has at least one
Kotlin-related attribute (`hasKotlinAttribute` is `true`).

We have only one function – for `list`, since only `list` is an ordered collection by default.

After implementing this task, the `Sort` button in the `list` mode becomes alive.

<div class="hint" title="Click me to view the expected state of the application after completing this task">

![Current state](../../utils/src/main/resources/images/duck/shop/states/state_9.gif)

</div>

If you have any difficulties, **hints will help you solve this task**.

----

### Hints

<div class="hint" title="Click me to learn how to sort a list of items">

You can use the built-in functions [`sortedBy`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/sorted-by.html) 
or [`sortedByDescending`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/sorted-by-descending.html),
which sort values in the list according to the returned value of the passed function:
```kotlin
val listOfWords = listOf("a", "bbbbb", "aa", "aaa", "aaaaaaaa")
println(listOfWords.sortedBy { it.length }) // [a, aa, aaa, bbbbb, aaaaaaaa]
```
In the provided example, the list is sorted according to the length of words.
</div>

<div class="hint" title="Click me to learn how to calculate the sum of elements in a list">

You can use the built-in [`sum`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/sum.html) function:
```kotlin
val listOfNumbers = listOf(1, 2, 3, 4, 5)
println(listOfNumbers.sum()) // 15
```

If you need to use some extra calculations, e.g., multiply the values of even numbers in the list by a coefficient, you can use the built-in [`sumOf`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/sum-of.html)` function:
```kotlin
val listOfNumbers = listOf(1, 2, 3, 4, 5)
println(listOfNumbers.sumOf { if (it % 2 == 0) it * 2 else it }) // 21
```
In the provided example, we calculated the sum of the following values: `1 + 2 * 2 + 3 + 4 * 2 + 5`.
</div>
