## Definition

`Maps` (or dictionaries) are sets of key-value pairs. 
The keys are unique, and each of them maps to exactly one value, 
while the values can be duplicated.

In general, `Map` contains _only_ elements with the _same_ type,
but types for keys and values can be different: e.g., `Duck` for keys and `Int` for values.
In some cases, it is possible to store elements with different types,
but there must be a _relation_ between them, see [documentation](https://kotlinlang.org/docs/generics.html) for more details.

![Map definition](../../utils/src/main/resources/images/duck/shop/theory/map_definition.png)

<div class="hint" title="Click me to learn different Map implementations">

Similar to the case of `List` and `Set`, you can use different `Map` implementations to change this property:
e.g., you can use the `LinkedHashMap` implementation, which is provided by default.
`LinkedHashMap` is a doubly linked HashMap that preserves the injection order on 
iteration. It uses the asymptotic `O(1)` for adding and reading by key, 
and it relies on the `hashCode()` function of elements, the same as the `LinkedHashSet` implementation.
</div>

## Initialization

To create a new map, you can use special _builders_:

```kotlin
val emptyMap1 = emptyMap<Int, String>() // Builds the internal object EmptyMap
val emptyMap2 = mapOf<Int, String>() // Calls emptyMap()
```

<div class="hint" title="Click me to learn the difference between an emptyMap and a standard map">

In Kotlin, the `emptyMap` implementation is an object under the hood.
The main reason such a function exists is to save allocations (creating and deleting new variables).
Since `emptyMap` returns the same _singleton_ instance every time it is called, one can
use it in an allocation-free manner. It allows your Kotlin program to work in a more efficient way.
</div>

```kotlin
val mapOfNumbers = mapOf(1 to "one", 2 to "two") // The type can be inferred, a set with elements 1 to "one", 2 to "two" will be created
```
