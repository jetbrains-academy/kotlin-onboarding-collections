Time to practise! 
In this exercise, you will need to create a read-only `commands` field in the already 
defined `GameService` class in the `org.jetbrains.kotlin.course.tamagotchi.game` package 
to store commands for a tamagotchi.
Initialize `commands` as a collection with no elements.

If you have any difficulties, **hints will help you solve this task**.

----

### Hints

<div class="hint" title="Click me to learn which type is most suitable for 'commands'">

Since the tamagotchi implies two modes - queue and stack - that can be changed during the game, 
the most appropriate collection is `ArrayDeque`. 
The `ArrayDeque` class provides constant time performance for inserting and 
removing items from both ends of the queue, making it a good choice for our scenario.
</div>
