## Definition

`Set`s are collections of _unique_ elements. 
They reflect the mathematical abstraction of `set`: 
a group of objects without duplicates.
It means, if we have two sets with the same size with the same objects inside, 
these two sets will be equal.

**TODO: img3**

<div class="hint" title="Different Set implementations">

Unlike `List`, `Set` normally does not preserve the order of items.
But similar to `List` you can use the different `Set` implementations to change this property,
e.g. you can use `LinkedHashSet` implementation, that sorts elements in the set by default.

Each implementation defines not only the set of possible operations with this collection,
but also the complexity of `this` operation. TODO: can we refer to the algorithms course?

TODO: add a link to read it in detail
</div>

In general `Set` contains _only_ elements with the _same_ type.
In some cases it is possible to store elements with different types,
but which have a _relation_ between them, see TODO for more details.

**TODO: img2**

If you have a set with objects, e.g. with your own type like `Duck`, 
it is possible to override behaviour to compare element in `Set`.
By default, to compare elements inside a set the [`euqals`](https://kotlinlang.org/docs/equality.html) function is used.
It is possible to override this function and compare items only by a part of possible properties.

<div class="hint" title="An example of comparing items in Set only by a part of possible properties">

Consider an example.

Let's have a `DuckNormal` class with several attributes:
```kotlin
data class DuckNormal(val backgroundColor: String, val accessories: List<String>)
```

Let's override the `equals` and `hashCode` methods to compare only `backgroundColor`:
```kotlin
data class DuckOverridden(val backgroundColor: String, val accessories: List<String>) {
    override fun equals(other: Any?) = backgroundColor == (other as? DuckOverridden)?.backgroundColor
  
    override fun hashCode() = backgroundColor.hashCode()
}
```

In this case, `DuckNormal` will be compared by `backgroundColor` and `accessories`, 
but `DuckOverridden` will be compared _only_ by `backgroundColor`:

**TODO: img4**

Since we compare only `backgroundColor`, the set in the second case consists only from one element.

</div>

## Initialization

To create a new set you can use special _builders_:

```kotlin
val emptySet1 = emptySet<Int>() // Builds the internal object EmptySet
val emptySet2 = setOf<Int>() // Calls emptySet()
```

<div class="hint" title="What is the difference between emptySet and a regular one?">

TODO
</div>

```kotlin
val setWithNumbers1 = setOf(1, 2, 3) // The type can be inferred, a set with elements 1, 2, 3 will be created
```
