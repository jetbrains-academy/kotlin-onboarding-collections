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

![Current state](../../utils/src/main/resources/images/master/chef/states/refil.gif)

</div>

If you have any difficulties, **hints will help you solve this task**.

----

### Hints

<div class="hint" title="Click me to learn how to generate a random boolean value">

To generate a random boolean value you can use [`Random.nextBoolean()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.random/-random/next-boolean.html) function from the Kotlin standard library.
</div>
