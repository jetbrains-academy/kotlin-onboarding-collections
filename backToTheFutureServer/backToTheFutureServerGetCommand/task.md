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

You can use the built-in functions [`removeFirst`](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayDeque.html#removeFirst) 
and [`removeLast`](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayDeque.html#removeLast) 
to remove and return the element.
</div>
