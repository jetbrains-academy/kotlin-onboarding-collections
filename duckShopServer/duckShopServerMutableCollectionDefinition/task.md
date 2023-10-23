## Definition

We have already learned how to create different collections in Kotlin, e.g., `List`, `Set`, or `Map`.
However, if you try to modify the collections that we've created by built-in builders (e.g., `listOf()`, etc.), 
you will get an error:

```kotlin
fun main() {
    val listOfNumbers = listOf(1, 2, 3)
    listOfNumbers.add(4) // ERROR

    val setOfNumbers = setOf(1, 2, 3)
    setOfNumbers.add(4) // ERROR

    val mapOfNumbers = mapOf(1 to "one", 2 to "two", 3 to "three")
    mapOfNumbers.put(4, "four") // ERROR
}
```

It happens since in Kotlin, all collections are divided into _read-only_ and _mutable_ ones.
If you have a _read-only_ collection, you can create it only once and then you can only _read_ elements 
from the collection: e.g., this code is correct:

```kotlin
fun main() {
    val listOfNumbers = listOf(1, 2, 3)
    println(listOfNumbers[1]) // 2
}
```

If you need to modify the collection somehow, you need to create a _mutable_ collection or transform a _read-only_ 
version into a _mutable_ one:

```kotlin
fun main() {
    val listOfNumbers = listOf(1, 2, 3)
    val mutableListOfNumbers = listOfNumbers.toMutableList()
    mutableListOfNumbers.add(4) // OK

    val setOfNumbers = mutableSetOf(1, 2, 3)
    setOfNumbers.add(4) // OK
}
```

Each mutable collection has _all_ methods from the read-only version, but extends them 
with such methods as `add`, `remove`, etc. 
You can find all of them in the official [Kotlin documentation](https://kotlinlang.org/docs/collections-overview.html).

## Build a mutable collection

As we mentioned above, you can _create_ a mutable collection or _transform_ an existing one.

To create mutable collections, you can use the built-in builders:

```kotlin
fun main() {
    val mutableList1 = mutableListOf<Int>() // Or: ArrayList<Int>()
    val mutableList2 = mutableListOf(1, 2, 3) // The type can be inferred

    val mutableSet1 = mutableSetOf<Int>() // Or: LinkedHashSet<Int>() or HashSet<Int>()
    val mutableSet2 = mutableSetOf(1, 2, 3) // The type can be inferred

    val mutableMap1 = mutableMapOf<Int, String>() // Or: LinkedHashMap<...>() or HashMap<...>()
    val mutableMap2 = mutableMapOf(1 to "one", 2 to "two") // The type can be inferred
}
```

If you need to _transform_ an existing collection, you can also use the built-in functions:

```kotlin
fun main() {
    val listOfNumbers = listOf(1, 2, 3)
    val mutableListOfNumbers = listOfNumbers.toMutableList()

    val setOfNumbers = setOf(1, 2, 3)
    val mutableSetOfNumbers = setOfNumbers.toMutableSet()

    val mapOfNumbers = mapOf(1 to "one", 2 to "two")
    val mutableMyMap = mapOfNumbers.toMutableMap()
}
```
