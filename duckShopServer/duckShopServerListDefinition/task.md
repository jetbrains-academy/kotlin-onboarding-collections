## Definition

A collection usually contains a number of objects 
(this number may also be zero) of the same type.  
Objects in a collection are called elements or items.  

`List`s are ordered collections with access to 
elements by indices – integer numbers that reflect their position. 
The index of the first element is zero.
Elements can occur more than once in a list.

**TODO: img1**

<div class="hint" title="Different List implementations">

  List may be implemented using an array or doubly linked list internally. 
  We could say that `List`, is an interface or specification determining which operations are supported 
  (for example, insert item, remove item, get item by index, etc.)
  and this interface can be backed by multiple implementations.

  Each implementation defines not only the set of possible operations with this collection, 
  but also the complexity of `this` operation. TODO: can we refer to the algorithms course?

  TODO: add a link to read it in detail
</div>

In general `List` contains _only_ elements with the _same_ type. 
In some cases it is possible to store elements with different types, 
but which have a _relation_ between them, see TODO for more details.

**TODO: img2**

## Initialization

To create a new list you can use special _builders_:

```kotlin
val emptyList1 = emptyList<Int>() // Builds the internal object EmptyList
val emptyList2 = listOf<Int>() // Calls emptyList()
```

<div class="hint" title="What is the difference between emptyList and a regular one?">

TODO
</div>

```kotlin
val listWithNumbers1 = listOf(1, 2, 3) // The type can be inferred, a list with elements 1 ,2, 3 will be created
```

In some cases you need to create a list with `N` elements 
where each element will be generated via a special function. 
In this case, you can use the `List` constructor and pass the number of elements and 
the function that generates these elements. This function will be called `N` times to fill out the list with `N` elements:

```kotlin
fun generateRandomInt(): Int = TODO("Not implemented yet")

val listWithNumbers2 = List(3) { generateRandomInt() } // A list with three random integer numbers will be created
```
