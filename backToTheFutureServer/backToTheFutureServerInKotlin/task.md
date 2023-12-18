The FIFO and LIFO approaches described above are often used in programming. 
Of course, it is not difficult to implement such an approach yourself: 
for example, by working with a list. However, many programming languages offer their own 
implementations that perform various types of optimisations. 
Kotlin is no exception.

Let's look at our example application. 
As shown in the first step, in our tamagotchi, the user can switch the game mode—queue or stack. 
This means that when we choose a data structure, 
it is important to us how quickly we get elements, 
not just from the beginning _or_ from the end, but in _both_ scenarios.

<div class="hint" title="Click me to view what the final application looks like">

![Final application](../../utils/src/main/resources/images/tamagotchi/states/ready.gif)

</div>

For such cases, the [`ArrayDeque`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-deque/) implementation is the best option for us.
The `ArrayDeque` class provides constant time performance for inserting and
removing items on both ends of the queue, making it a good choice for our scenario.

To create a new empty instance of `ArrayDeque`, you can invoke its constructor:
```kotlin
fun main() {
    val arrayDequeExample = ArrayDeque<Int>() // Create an empty ArrayDeque
}
```

**Note**, if you add the following import at the top of the file
```kotlin
import java.util.ArrayDeque
```
and keep the same `ArrayDeque` definition, you will use the Java [`ArrayDeque`](https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/java/util/ArrayDeque.html)
implementation, which has different names of methods, but the main meaning remains the same. 
_In Kotlin, it is better to use the Kotlin implementation._


This collection is _mutable_ by default. 
This means that despite the fact that we have the read-only keyword `val`, 
we can update the collection itself. 
In this case, we just cannot _reassign_ the variable.
