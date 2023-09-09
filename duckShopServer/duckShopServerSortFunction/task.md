In this task you need to implement a function to be able to
sort ducks in the Duck Shop according to the price of the stuff.
This step is the final step to finish this application.

### Task

Implement the `sortDucks` function from the `GameActionFunctionsService` class in
the `org.jetbrains.kotlin.course.duck.shop.functions.action` package.
This function should sort ducks in the list by descending according to the sum of duck's stuff.
To calculate the duck's price you need to sum prices for all accessories for a duck. 
Please use an extra coefficient `100` for each accessory if the duck has at least one
Kotlin stuff (`hasKotlinAttribute` is `true`).

We have only one function for `list`, since only `list` is an ordered collection by default.

After implementing this task the `Sort` button in the `list` mode becomes alive:

![Current state](../../utils/src/main/resources/images/duck/shop/states/state_9.gif)

If you have any difficulties, **hints will help you solve this task**.

----

### Hints

[//]: # (        val sortedDucks = initialDucks.sortedByDescending { d ->)

[//]: # (            d.accessories.sumOf {)

[//]: # (                val coefficient = if &#40;d.hasKotlinAttribute&#41; 100 else 1)

[//]: # (                it.price * coefficient)

[//]: # (            })

[//]: # (        })

<div class="hint" title="How to sort a list of items">

You can use [`sortedBy`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/sorted-by.html) 
or [`sortedByDescending`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/sorted-by-descending.html) built-in functions
that sort values in the list according to the returned value of the passed function:
```kotlin
val listOfWords = listOf("a", "bbbbb", "aa", "aaa", "aaaaaaaa")
println(listOfWords.sortedBy { it.length }) // [a, aa, aaa, bbbbb, aaaaaaaa]
```
In the provided example the list is sorted according to the length of words.
</div>

<div class="hint" title="How to calculate the sum of elements in a list?">

You can use the [`sum`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/sum.html) built-in function:
```kotlin
val listOfNumbers = listOf(1, 2, 3, 4, 5)
println(listOfNumbers.sum()) // 15
```

If you need to use some extra calculations, e.g. multiple even numbers values in the list on a coefficient, you can use the [`sumOf](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/sum-of.html)` built-in function:
```kotlin
val listOfNumbers = listOf(1, 2, 3, 4, 5)
println(listOfNumbers.sumOf { if (it % 2 == 0) it * 2 else it }) // 21
```
In the provided example we calculated the sum of the following values: `1 + 2 * 2 + 3 + 4 * 2 + 5`.
</div>
