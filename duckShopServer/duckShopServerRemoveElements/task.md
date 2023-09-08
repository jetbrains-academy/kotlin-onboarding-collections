In this task you need to implement functions to be able to 
remove ducks from the Duck Shop.

### Task

Implement three versions of the `removeRandomDuck` function from the `GameChangeFunctionsService` class in
the `org.jetbrains.kotlin.course.duck.shop.functions.change` package.
These functions should remove a random duck from a list, set, or a map.

After implementing this task the `Remove` button in all three modes becomes alive:

![Current state](../../utils/src/main/resources/images/duck/shop/states/state_4.gif)

If you have any difficulties, **hints will help you solve this task**.

----

### Hints

<div class="hint" title="What extension functions are?">

  The `removeRandomDuck` functions are implemented as [extension](https://kotlinlang.org/docs/extensions.html#extension-functions) functions.
  These functions allow you to extend _any_ class with yur own functionality, e.g.

```kotlin
fun List<Duck>.removeRandomDuck(): List<Duck> = TODO("Not implemented yet")
```
is the same with
```kotlin
fun removeRandomDuck(listOfDucks: List<Duck>): List<Duck> = TODO("Not implemented yet")
```
but in the first example you can call `removeRandomDuck` function directly from a list of ducks, e.g.
```kotlin
myList.removeRandomDuck()
```
In the second one you need to pass the list as an argument:
```kotlin
removeRandomDuck(myList)
```
</div>


 <div class="hint" title="How to convert a collection to a mutable one">

You can use `toMutableList`, `toMutableSet`, and `toMutableMap` built-in functions:
```kotlin
fun main() {
    val listOfNumbers = listOf(1, 2, 3)
    val mutableListOfNumbers = listOfNumbers.toMutableList()

    val setOfNumbers = setOf(1, 2, 3)
    val mutableSetOfNumbers = setOfNumbers.toMutableSet()

    val myMap = mapOf(1 to "one", 2 to "two")
    val mutableMyMap = myMap.toMutableMap()
}
```
 </div>

<div class="hint" title="How to generate a random element from a list, set, and map?">

You can use different built-in functions. The first way to generate a random element from a list 
is to generate a random index and get an element from the list of this index:
```kotlin
val listOfNumbers = listOf(1, 2, 3)
println(listOfNumbers[listOfNumbers.indices.random()])
```

The second way is shorter since you can generate a random element directly by a collection:
```kotlin
val listOfNumbers = listOf(1, 2, 3)
println(listOfNumbers.random())

val mapOfNumbers = mapOf(1 to "one", 2 to "two")
println(mapOfNumbers[mapOfNumbers.keys.random()])
```
</div>

<div class="hint" title="How to remove an element from a list, set, and map?">

You can use different built-in functions to delete elements from the collections.
The first way is to delete an element from a list _by index_:
```kotlin
val listOfNumbers = mutableListOf(1, 2, 3)
listOfNumbers.removeAt(0)
println(listOfNumbers) // 2, 3
```

You cannot invoke this function with a set or a map since they are unordered collections.

The second way is valid for all three types of collections - you can delete an element 
directly via the `remove` built-in function:
```kotlin
    val listOfNumbers = mutableListOf(1, 2, 3)
listOfNumbers.remove(2) // Remove item: 2

val mapOfNumbers = mutableMapOf(1 to "one", 2 to "two")
mapOfNumbers.remove(1) // Remove item: 1 to "one"
```
</div>


 