In this task you need to implement a function
to be able to initialize a set of random ducks in the Duck Shop.

### Task

Implement `generateSetOfDucks` function from the `GameModeService` class in
the `org.jetbrains.kotlin.course.duck.shop.mode` package.
This function should generate a new set with `MAX_NUMBER_OF_DUCKS` random ducks.

You can find the already defined variable `MAX_NUMBER_OF_DUCKS`
in the `GameUtil.kt` file in the `org.jetbrains.kotlin.course.duck.shop.utils` package.

After implementing this task the `Set` button becomes alive:

![Current state](../../utils/src/main/resources/images/duck/shop/states/state_2.gif)

If you have any difficulties, **hints will help you solve this task**.

----

### Hints

<div class="hint" title="How can I shuffle a list of elements?">

If you need to shuffle a list of elements, you can use the built-in function [`shuffled`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/shuffled.html).

Since `Duck.values()` returns [`kotlin.Array`](https://kotlinlang.org/docs/arrays.html), by default you cannot invoke the `shuffled` function, you need to convert the result into a list:
```kotlin
Duck.values().shuffled() // ERROR

Duck.values().toList().shuffled() // OK
```

We will not consider the detailed difference between lists and arrays in this project, you can follow to the documentation to get more details.
</div>

<div class="hint" title="How can I take N first elements from a list?">

To take first `N` elements from the list, you can use the [`take`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/take.html) built-in function.
Note, since `List` is an ordered structure, you always will get first `N` elements.
To complete the task you can take the necessary amount of shuffled elements and convert the result into a set.
Since the `Duck` enum has only different value, the resulting size of the set will be the same with the list size.
</div>

<div class="hint" title="How can I convert a list to a set">

You can use the built-in function [`toSet`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/to-set.html) to convert a list to a set.
</div>
