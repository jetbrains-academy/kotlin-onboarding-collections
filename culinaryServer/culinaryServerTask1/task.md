
______________
______________
______________

Master Chef - Fridge Refill (Coding task)


______________

Master Chef - List vs. Sequence (Theory task)

The main purpose of this project is to show the difference between regular collections 
(in our example `List`) with `Sequence`s.
**If you are noe familiar with Kotlin collections, we recommend you to learn them with the previous projects on this course and come back then.**

Unlike collections, sequences _don't contain elements_, they produce them while iterating. 
Sequences implement the approach to multi-step collection processing.
It means, that when you operate with sequences you combine all the operations that should be done in one action.

Consider an example with cooking.
Let's imagine that we need to cook a tomato soup. 
To make it simple, we will use only tomatoes.
To ook this sup, we need to pull tomatoes from the fridge, put them into the kitchen, cut them, and then add into a pot.

If we work with a _list_ of tomatoes, then we will do it exactly as we described, 
since we will be working with all the tomatoes per action, e.g., get all tomatoes, then cut all the tomatoes, etc:

![Current state](TODO: picture that describes this process)

If we work with a _sequence_ of tomatoes, then we will handle each tomato separately until we get the necessary number of tomatoes, e.g.
we will get one tomatoe, put it into the kitchen, cut it, put into the pot. Then we will repeat this action with the next tomato, and etc:

![Current state](TODO: picture that describes this process)

The first approach works good in the case, when you have enough space in the kitchen, but if you don't, 
probably the second one will be better - since it cn handle a long _sequence_ of tomatoes with a limited space in the kitchen.

![Current state](TODO: picture 1 case when many tomatoes and do not have enough space on the table)

This way of computing is called `lazy computing` and it is the main idea of the sequences.

______________

Master Chef - Cook Tomato Soup (Coding task)

Let's practice! Let's start with the familiar approach with collections cook the tomato soup from the previous lesson with a _list_ of tomatoes.
In this task we will make the `Cook Tomato Soup` button live.

In this task, you need to implement two functions in the `TomatoSoup.kt` file:

- `getTomatoesForSoup` - which generates a _list_ of `NUMBER_OF_TOMATOES` tomatoes (a `VegetableType` with type `VegetableType.Tomato`)
- `prepareTomatoes` - that accept a _list_ of tomatoes, and do the following with each item frm the list:

  - put it into the kitchen (see `KitchenImpl.put`)
  - cut each element (see `KitchenImpl.cut`)
  - take each cut tomato (see `KitchenImpl.take`)
  - put each tomato into the pot (see `PotImpl.put`)

<div class="hint" title="Click me to view the expected state of the application after completing this task">

![Current state](TODO)

</div>

______________

Master Chef - Sequence Initialization (Theory task)

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
