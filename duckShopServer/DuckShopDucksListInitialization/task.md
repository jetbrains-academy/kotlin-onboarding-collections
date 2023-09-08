It is time for practice! In this task you need to implement a 
functions to be able to initialize a list of random ducks in the Duck Shop.

### Task

First of all implement the `generateRandomDuck` function from `Duck.kt` file 
inside the `org.jetbrains.kotlin.course.duck.shop.duck` package. 
This function should get all values from the `Duck` class in the same file and 
return a random duck from this list.

Next, implement `generateListOfDucks` function from the `GameModeService` class in 
the `org.jetbrains.kotlin.course.duck.shop.mode` package. 
This function should generate a new list with `MAX_NUMBER_OF_DUCKS` random ducks. 
To generate a random duck, please, use the `generateRandomDuck` function.

You can find the already defined variable `MAX_NUMBER_OF_DUCKS` 
in the `GameUtil.kt` file in the `org.jetbrains.kotlin.course.duck.shop.utils` package.

After implementing this task the `List` button becomes alive:

![Current state](../../utils/src/main/resources/images/duck/shop/states/state_1.gif)

If you have any difficulties, **hints will help you solve this task**.

----

### Hints

<div class="hint" title="How to get all values from an enum class?">

  To get all values from an enum class you can use a built-in function [`values`](https://kotlinlang.org/docs/enum-classes.html#working-with-enum-constants).
</div>


<div class="hint" title="How to get a random item from a list?">

You can use a special built-in function [`random`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/random.html), 
which works with a _list_ and allows to extract a random value from it.
</div>

<div class="hint" title="How to create a list with N items?">

You can call a constructor from the `List` class directly with the number of elements that should be generated:
```kotlin
val N = 5
fun generateRandomInt(): Int = TODO("Not implemented yet")

val listWithNumbers2 = List(N) { generateRandomInt() } // A list with N random integer numbers will be created
```
</div>
