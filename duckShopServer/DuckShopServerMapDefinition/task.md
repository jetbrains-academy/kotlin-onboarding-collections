## Definition

`Map`s (or dictionaries) are sets of key-value pairs. 
The keys are unique, and each of them maps to exactly one value, 
while the values can be duplicated.

In general `Map` contains _only_ elements with the _same_ type,
but types for keys and values can be different, e.g. `Duck` for keys and `Int` for values.
In some cases it is possible to store elements with different types,
but which have a _relation_ between them, see [documentation](https://kotlinlang.org/docs/generics.html) for more details.

![Map definition](../../utils/src/main/resources/images/duck/shop/theory/map_definition.png)

<div class="hint" title="Different Map implementations">

Similar to `List` and `Set` you can use the different `Map` implementations to change this property,
e.g. you can use `LinkedHashMap` implementation, that is provided by default.
`LinkedHashMap` is a doubly linked HashMap that preserves the injection order on 
iteration. It uses Asymptotic `O(1)` for adding and reading by key, 
and it relies on the `hashCode()` function of elements, the same with the `LinkedHashSet` implementation.
</div>

## Initialization

To create a new map you can use special _builders_:

```kotlin
val emptyMap1 = emptyMap<Int, String>() // Builds the internal object EmptyMap
val emptyMap2 = mapOf<Int, String>() // Calls emptyMap()
```

<div class="hint" title="What is the difference between emptyMap and a regular one?">

In Kotlin the `emptyMap` implementation is an object under the hood.
The main reason such function exists is to save allocations (creating and deleting new variables).
Since `emptyMap` returns the same _singleton_ instance every time it is called so one can
use it in allocation free manner. It allows to work your Kotlin program in a more efficient way.
</div>

```kotlin
val mapWithNumbers1 = mapOf(1 to "one", 2 to "two") // The type can be inferred, a set with elements 1 to "one", 2 to "two" will be created
```