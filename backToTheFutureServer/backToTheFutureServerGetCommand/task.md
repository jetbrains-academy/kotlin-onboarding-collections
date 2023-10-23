Thanks to this step, our application comes to alive with the commands' storage.
In this task you need to implement the `getCommand` function in the already implemented `GameService`
class in the `org.jetbrains.kotlin.course.tamagotchi.game` package.

This function should return `null` if `commands` is empty. 
Otherwise, it should get a command from `commands` according to the `mode`.

After implementing this task, the commands storage will work properly:

![Final application](../../utils/src/main/resources/images/tamagotchi/states/ready.gif)

If you have any difficulties, **hints will help you solve this task**.

----

### Hints

<div class="hint" title="How to get the first and the last elements?">

You can use the built-in functions [`removeFirstOrNull`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-deque/remove-first-or-null.html) 
and [`removeLastOrNull`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-deque/remove-last-or-null.html) 
to remove and return the element. Both these functions return `null` if the collection is empty.
</div>
