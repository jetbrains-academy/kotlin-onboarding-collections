In Kotlin, you can work with collections in a different way.
You can perform not only basic operations, like sorting or finding some elements,
but also more complex ones.
One such operation is _grouping_ items by a condition.

The easiest way to group elements is to use the built-in [`groupBy`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/group-by.html) function.

This function allows you to group elements of the original collection by the 
key returned by the given `keySelector` function, which is applied to each 
element and returns a map where each group key is associated with a list of corresponding elements.

In simple words, if you group objects, for example, by their length, 
then the keys in the resulting map will be the possible length 
values found in the original collection, and for each such key, a 
list of objects from the original collection with a given length will be compiled.

Consider an example:
```kotlin
fun main() {
    val words = listOf("a", "abc", "ab", "def", "abcd")
    words.groupBy { it.length }.map { (length, words) ->
        val wordsStr = words.joinToString(", ")
        println("Current length is $length. The words: $wordsStr")
    }
}
```

Since we group the strings from the list by their length, this code will print into the console the following output:
```text
Current length is 1. The words: a
Current length is 3. The words: abc, def
Current length is 2. The words: ab
Current length is 4. The words: abcd
```
