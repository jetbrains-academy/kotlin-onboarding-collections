Let's practice and add spices to our soup! In this task we will practice with sequences.
In this task we will make the `Spice` and `Taste` buttons live. 

You need to implement two functions in the `TomatoSoup.kt` file:

- `generateSpices` - which generates a _sequence_ of random spices
- `addSpices` - that accept a _sequence_ of spices, and do the following with each item from the sequence:

    - get the spice from the self (see `ShelfImpl.getSpice`)
    - put each spice into the pot (see `PotImpl.put`)

Afterward you need to _take_ a random number from 1 to 4 of spices from the sequence and perform the described actions.

**Note that if you see the terrible taste message in the application, it does not mean that you have done something wrong.
It means that the number of spices generated was too high.**

<div class="hint" title="Click me to view the expected state of the application after completing this task">

![Current state](../../utils/src/main/resources/images/master/chef/states/spice.gif)

</div>

If you have any difficulties, **hints will help you solve this task**.

----

### Hints

<div class="hint" title="Click me to learn how to use API functions">

The game has the `GameEnviroment.kt` file which contains _instances_ of all possible objects, e.g.
it has `shelf` variable for `ShelfImpl` and to use `ShelfImpl` API you need to call it with `shelf`:
```kotlin
shelf.getSpice(...)
```
</div>
