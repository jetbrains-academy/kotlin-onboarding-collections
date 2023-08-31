## Definition

`Map`s (or dictionaries) are sets of key-value pairs. 
The keys are unique, and each of them maps to exactly one value, 
while the values can be duplicated.

In general `Map` contains _only_ elements with the _same_ type,
but types for keys and values can be different, e.g. `Duck` for keys and `Int` for values.
In some cases it is possible to store elements with different types,
but which have a _relation_ between them, see TODO for more details.

**TODO: img5**

<div class="hint" title="Different Map implementations">

TODO: add a link to read it in detail
</div>

## Initialization

To create a new map you can use special _builders_:

```kotlin
val emptyMap1 = emptyMap<Int, String>() // Builds the internal object EmptyMap
val emptyMap2 = mapOf<Int, String>() // Calls emptyMap()
```

<div class="hint" title="What is the difference between emptyMap and a regular one?">

TODO
</div>

```kotlin
val mapWithNumbers1 = mapOf(1 to "one", 2 to "two") // The type can be inferred, a set with elements 1 to "one", 2 to "two" will be created
```
