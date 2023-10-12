Time to practise! 
In this exercise you will need to create a read-only `commands` field in the already 
defined `GameService` class in the `org.jetbrains.kotlin.course.tamagotchi.game` package 
to store commands for tamagotchi.
Initialize `commands` as a collection with no elements.

If you have any difficulties, **hints will help you solve this task**.

----

### Hints

<div class="hint" title="Which type is most suitable for 'commands'?">

Since tamagotchi implies two modes - queue and stack - which can be changed during the game, 
the most appropriate collection is `ArrayDeque`. 
The `ArrayDeque` class provides constant time performance for inserting and 
removing items from both ends of the queue, making it a good choice for our scenario.
</div>
