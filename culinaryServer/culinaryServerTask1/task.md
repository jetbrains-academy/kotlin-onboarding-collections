Master Chef - Introduction (Theory task)

### Project description


The project of this lesson is **Master Chef**.
Have you ever dreamed of becoming a master chef? 
In this application all your dreams will come true, and you will be able 
to cook delicious dishes and learn programming. Isn't it great?

### Lesson topics

- Working with pre-rewritten API
- List vs Sequence

### Project example

By the end of this task, you will create the following application:

![Final application](TODO)

______________

Master Chef - How to Run (Theory task)

At each stage, you can run the current version of the application.
However, if some functionality is not yet ready,
then some buttons may not work and some information may not be displayed.

To run the application, you need to run the `main` function inside
the [MasterChefApplication.kt](psi_element://org.jetbrains.kotlin.course.culinary.MasterChefApplicationKt#main) file:

![How to run the application](TODO)

Please don't forget to _stop all other runs_ by pressing the red square button:

![How to stop the application](TODO)

Next, you need to open any browser (we recommend using [Google Chrome](https://www.google.com/chrome/) to display the elements as in the examples)
and open http://localhost:8080/. You will see the main page of the application.

<div class="hint" title="Click me to view what the main page of the application looks like">

![The main page of the application](TODO)

</div>

<div class="hint" title="Click me if the application from the last launch is displayed">

If an application from a previous launch is displayed on the screen when starting the game, you need to reset the caches.
This can usually be done with a keyboard shortcut: `ctrl` + `shift` + `R` (`command` + `shift` + `R` for macOS).
</div>

______________

Master Chef - The Kitchen, Models (Theory task)

To work in our kitchen, you need to understand what API (classes and functions) we already have, 
because throughout this lesson you will be improving that API.

Firstly, we have a set of _models_ which represent all the items in our kitchen.
All models are stored in the `models' folder and are divided into three categories:
- _food_ - models that represent all the possible foods we have.
- _storage_ - models representing places where food is stored, such as a fridge or a shelf.
- _cooking_ - models representing kitchen utensils for cooking, such as a pot.

Let's start with the _food_ models. Basically we have vegetables, fruits and species.
Vegetables can be fresh or not, cut or in their original form. 
**Check all the possible models before going to the next steps, 
it will help you when we refer to them in the tasks.**

![Food models](TODO: picture with all possible food)

Then we have the _storage_ models.
We only have three types of storage: a fridge that can only store vegetables, 
a basket for storing fruit and a shelf for storing spices.
**Check all the possible models before going to the next steps,
it will help you when we refer to them in the tasks.**

![Food models](TODO: picture with all possible storages)

Finally, we have the _cooking_ models. 
These will help us to cook our dishes. 
As you can see, they are implemented as interfaces, 
and you will be implementing some of these functions yourself during this lesson.
**Check all the possible models before going to the next steps,
it will help you when we refer to them in the tasks.**

![Food models](TODO: picture with all possible kitchen utensils)

______________

Master Chef - Fridge Refill (Coding task)


Woohoo! It's coding time! Let's start with a simple task. 
To make our cooking possible, we need to refill the fridge with vegetables. 
In this task we will make the `Refill` button live, which will remove all the old vegetables and add new ones.

The `FridgeImpl` object has a mutable list of `vegetables` to store.
In this task you need to implement two methods inside `FridgeImpl`:

- `generateRandomVegetables` - that should build a list of `Vegetable` by the following rules:

    - generates `RANDOM_VEGETABLES_NUMBER` random vegetables with random value for `isFresh` property
    - generates `RANDOM_FRESH_VEGETABLES_NUMBER` random not fresh vegetables (with `isFresh = True` property)

- `refill` - that should clear all the vegetables from the list of `vegetables`, 
generates new ones with the `generateRandomVegetables` function and add them into the list of `vegetables` 

<div class="hint" title="Click me to view the expected state of the application after completing this task">

![Current state](TODO)

</div>

If you have any difficulties, **hints will help you solve this task**.

----

### Hints

<div class="hint" title="Click me to learn how to generate a random boolean value">

To generate a random boolean value you can use [`Random.nextBoolean()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.random/-random/next-boolean.html) function from the Kotlin standard library.
</div>

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
