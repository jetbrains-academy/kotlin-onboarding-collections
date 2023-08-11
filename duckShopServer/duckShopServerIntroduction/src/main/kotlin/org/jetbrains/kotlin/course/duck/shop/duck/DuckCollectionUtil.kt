package org.jetbrains.kotlin.course.duck.shop.duck

fun MutableSet<Duck>.addOneDuck(): Duck {
    var wasAdded: Boolean
    var duck: Duck
    do {
        duck = generateRandomDuck()
        wasAdded = this.add(duck)
    } while (!wasAdded)
    return duck
}

fun MutableMap<Duck, String>.addOneDuck(): Pair<Duck, String> {
    var duck: Duck
    var description: String
    val oldSize = this.size
    do {
        duck = generateRandomDuck()
        description = duck.getDescription()
        this.putIfAbsent(duck, description)
    } while (this.size - 1 != oldSize)
    return Pair(duck, description)
}
