In this task you need to implement functions to be able to
add ducks to the Duck Shop.

### Task

Implement three versions of the `addRandomDuck` function from the `GameChangeFunctionsService` class in
the `org.jetbrains.kotlin.course.duck.shop.functions.change` package.
These functions should add a random duck from a list, set, or a map.

Note, you need _actually_ to add a new duck, it means for the cases with a set and a map you 
need to generate a duck that is not present in the collection and add it.

After implementing this task the `Add` button in all three modes becomes alive:

![Current state](../../utils/src/main/resources/images/duck/shop/states/state_5.gif)

If you have any difficulties, **hints will help you solve this task**.

----

### Hints

<div class="hint" title="How to add a new item into a list or set?">

To add a new item you can use the `add` built-in function:
```kotlin
val listOfNumbers = mutableListOf(1, 2, 3)
listOfNumbers.add(4)
println(listOfNumbers) // 1, 2, 3, 4

val setOfNumbers = mutableSetOf(1, 2, 3)
setOfNumbers.add(4)
println(setOfNumbers) // 1, 2, 3, 4
```

Note, that if you try to add the _same_ element to the set, it will not be added.
</div>

<div class="hint" title="How to add a new item into a map?">

You can associate a new key with a new value:
```kotlin
val myMap = mutableMapOf(1 to "one", 2 to "two")
myMap[3] = "three"
println(myMap) // 1 to "one", 2 to "two", 3 to "three"
```

In this case if you try to use an existing key, the old key will be replaced with the new one.

If you need to put something only if it is absent, you can use the `putIfAbsent` built-in function:
```kotlin
val myMap = mutableMapOf(1 to "one", 2 to "two")
myMap.putIfAbsent(3, "three")
myMap.putIfAbsent(3, "three") // Will not be added
println(myMap) // 1 to "one", 2 to "two", 3 to "three"
```
</div>

<div class="hint" title="How to find the difference between two lists?">

You can use the `minus` built-in function, to make the call more efficient convert one of the argument to the set:
```kotlin
val listOfNumbers1 = listOf(1, 2, 3, 4, 5)
val listOfNumbers2 = listOf(1, 2, 3)
println(listOfNumbers1.minus(listOfNumbers2.toSet())) // 4, 5
```
</div>
