Let's come back to the sequences. As we mentioned before, sequences do not _store_ elements.
It influences several rules to work with the sequences:

1) Usually the sequence is initialized by some _rule_ of how the next element will be generated. For example, if we already boiled tomatoes and want to add some species, the rule will be "extracting the next spice jar from the shelf".

   **Note that this is not always true**, you can convert a list to a sequence to perform all actions together. But it will not be a good idea for fast operations with small collections, because you will also need to spend some resources for the conversion to sequence and back.

2) If you work with the sequences, you always need to invoke one of the _terminal_ functions to actually run all the actions.

   Note, that sequences can be indefinite, so be careful when you call a _terminal_ function with no size limitation.

Consider examples for both cases. Let's imagine that we need to generate a sequence of species for our tomato soup.
We can use [`generateSequence`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/generate-sequence.html) function with a condition inside:

```kotlin
val spices = generateSequence{ shelfWithSpices.getNextValidOne(...) }
```

In this case we will not actually have these `spices`, `shelfWithSpices.getNextValidOne(...)` will be called each time when it will be necessary, i.e. when we will actually need the next jar of species.
For example, if for each jar of species we need to add it into the soup and we want to add only 3 different species, it will be done in the following way:

![Current state](TODO: a picture where we get and handle each piece step by step)

Consider the second example. A _terminal_ function is necessary to show the Kotlin compiler, that you actually ready to proceed with the actions, e.g. `take(5)` or `toList()` for finite sequences.
From the example above, if we don't use `take(5)` and in theory have infinite shelf, we will not be able to handle all of them, we do have limited resources on our laptop (or in a pot from the example).

![Current state](TODO: a picture - limited size of the pot, and we cannot handle all of them)
