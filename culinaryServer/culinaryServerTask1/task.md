Master Chef - Introduction (Theory task)

### Project description


The project for this lesson is **Master Chef**.
Have you ever dreamed of becoming a master chef? 
In this application, you'll get a chance to make that dream come true by 
cooking delicious dishes while learning programming. Isn't that exciting?

### Lesson topics

- Working with a pre-rewritten API
- List vs. Sequence

### Project example

By the end of this task, you will have created the following application:

![Final application](TODO)

______________

Master Chef - How to Run (Theory task)

At each stage, you can run the current version of the application.
However, if some functionality is not yet ready,
certain buttons may not work, and some information may not be displayed.

To run the application, execute the `main` function inside
the [MasterChefApplication.kt](psi_element://org.jetbrains.kotlin.course.culinary.MasterChefApplicationKt#main) file:

![How to run the application](TODO)

Please remember to _stop any previous runs_ by pressing the red square button:

![How to stop the application](TODO)

Next, open a browser (we recommend using [Google Chrome](https://www.google.com/chrome/) to display the elements as in the examples)
and go to http://localhost:8080/. This will display the main page of the application.

<div class="hint" title="Click me to view what the main page of the application looks like">

![The main page of the application](TODO)

</div>

<div class="hint" title="Click me if the application from the previous launch is displayed">

If an application from a previous launch is displayed when starting the game, you may need to reset the caches.
This can usually be done with the keyboard shortcut: `ctrl` + `shift` + `R` (`command` + `shift` + `R` for macOS).
</div>

______________

Master Chef - The Kitchen, Models (Theory task)

To work in our kitchen, you need to understand the available API (classes and functions), 
as you will be enhancing this API throughout the lesson.

Firstly, we have a set of _models_ representing all the items in our kitchen.
These models are stored in the `models' folder and are categorized as follows:
- _food_: Models representing all possible foods.
- _storage_: Models representing storage locations, such as a fridge or a shelf.
- _cooking_: Models representing kitchen utensils, such as a pot.

Let's start with the _food_ models. These include vegetables, fruits, and spices.
Vegetables can be fresh or not, whole or cut. 
**Familiarize yourself with all the available models before proceeding, 
as this will be helpful when they are referenced in tasks.**

![Food models](TODO: picture with all possible food)

Next, we have the _storage_ models.
There are three types of storage: a fridge that can only store vegetables, 
a basket for storing fruit, and a shelf for storing spices.
**Familiarize yourself with all the available models before proceeding, 
as this will be helpful when they are referenced in tasks.**

![Storage models](TODO: picture with all possible storages)

Finally, we have the _cooking_ models. 
These are used to cook our dishes. 
As you can see, they are implemented as interfaces, 
and during the lesson, you will implement some of these functions yourself.
**Familiarize yourself with all the available models before proceeding, 
as this will be helpful when they are referenced in tasks.**

![Cooking models](TODO: picture with all possible kitchen utensils)

______________

Master Chef - Fridge Refill (Coding task)


Woohoo! Let's get coding! We'll start with a simple task: 
refilling the fridge with vegetables. 
In this task, you'll make the `Refill` button functional by removing old vegetables and adding new ones.

The `FridgeImpl` object contains a mutable list of `vegetables`.
In this task, you need to implement two methods inside `FridgeImpl`:

- `generateRandomVegetables`: Generates a list of vegetables based on the following rules:

    - Produces `RANDOM_VEGETABLES_NUMBER` random vegetables with random freshness (`isFresh` property).
    - Produces `RANDOM_FRESH_VEGETABLES_NUMBER` random fresh vegetables (`isFresh = True` property).

- `refill`: Clears the current list of `vegetables`, 
generates new ones using the `generateRandomVegetables` function, and adds them to the list. 

<div class="hint" title="Click me to view the expected state of the application after completing this task">

![Current state](TODO)

</div>

If you have any difficulties, **hints will help you solve this task**.

----

### Hints

<div class="hint" title="Click me to learn how to generate a random boolean value">

To generate a random boolean value, you can use the [`Random.nextBoolean()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.random/-random/next-boolean.html) function from the Kotlin standard library.
</div>

______________

Master Chef - List vs. Sequence (Theory task)

The main purpose of this project is to demonstrate the difference between regular collections 
(like `List`) and `Sequence`.
**If you are not yet familiar with Kotlin collections, we recommend revisiting the previous projects in this course before continuing.**

Unlike collections, sequences _don't contain elements_; they produce them during iteration. 
Sequences implement a method for multi-step collection processing,
which means that when operating with sequences, all the operations are combined into a single action.

Consider this example invloving cooking
tomato soup. 
Let's simplify it by using only tomatoes.
To cook this soup, we need to pull tomatoes from the fridge, place them in the kitchen, cut them, and then add them to the pot.

If we work with a _list_ of tomatoes, we'll perform 
each step for all the tomatoes simultaneously—pull them all out, cut them all, and so on:

![Current state](TODO: picture that describes this process)

In contrast, when working with a _sequence_ of tomatoes, each tomato is handled individually until we have the required amount. For example,
we pull one tomato, place it in the kitchen, cut it, and put it into the pot before moving on to the next tomato:

![Current state](TODO: picture that describes this process)

The first approach works well if you have enough space in the kitchen, but if space is limited, 
the second method might be more efficient, since it handles each tomato sequentially, requiring less space.

![Current state](TODO: picture 1 case with many tomatoes and not enough space on the table)

This method of computing is called `lazy computing`, which is the primary concept behind sequences.

______________

Master Chef - Cook Tomato Soup (Coding task)

Now let's practice! We'll start by using collections to cook tomato soup, as we did in the previous lesson.
In this task, you'll make the `Cook Tomato Soup` button functional.

You need to implement two functions in the `TomatoSoup.kt` file:

- `getTomatoesForSoup`: Generates a _list_ of `NUMBER_OF_TOMATOES` tomatoes (a `VegetableType` with `VegetableType.Tomato`).
- `prepareTomatoes`: Accepts a _list_ of tomatoes and performs the following action for each:

  - Places it in the kitchen (`KitchenImpl.put`).
  - Cuts the tomato (`KitchenImpl.cut`).
  - Takes the cut tomato (`KitchenImpl.take`).
  - Places the tomato in the pot (`PotImpl.put`).

<div class="hint" title="Click me to view the expected state of the application after completing this task">

![Current state](TODO)

</div>

______________

Master Chef - Sequence Initialization (Theory task)

Returning to sequences, as mentioned earlier, sequences do not _store_ elements.
This fact introduces several rules for working with them:

1) Sequences are typically initialized with a _rule_ that dictates how the next element will be generated. For example, if we have already boiled the tomatoes and want to add some spices, the rule could be be "extract the next spice jar from the shelf".

   **Note that this rule is not always necessary**—you can convert a list to a sequence to perform all actions at once. However, this isn't ideal for fast operations with small collections, as the conversion to a sequence and back requires additional resources.

2) When working with sequences, you must always invoke a _terminal_ function to execute the operations. 

    Note that sequences can be infinite, so be cautious when calling a _terminal_ function without a size limit. 

Consider examples for both scenarios. Imagine generating a sequence of spices for our tomato soup.
We could use the [`generateSequence`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/generate-sequence.html) function with a condition:

```kotlin
val species = generateSequence{ shelfWithSpecies.getNextValidOne(...) }
```

In this case, the `spices` aren't actually retrieved until needed. Each time the next jar of spices is required, `shelfWithSpecies.getNextValidOne(...)` is called.
For example, if we need to add 3 different spices to the soup, this process will be as follows:

![Current state](TODO: a picture where we get and handle each piece step by step)

In the second example, a _terminal_ function is necessary to inform the Kotlin compiler that you are ready to proceed with the operations, such as using `take(5)` or `toList()` for finite sequences.
If we don't use `take(5)`, and if the shelf were infinite, we wouldn't be able to handle all the jars due to limited resources (whether on your laptop or in the pot from the example).

![Current state](TODO: a picture - limited size of the pot, and we cannot handle all of them)
