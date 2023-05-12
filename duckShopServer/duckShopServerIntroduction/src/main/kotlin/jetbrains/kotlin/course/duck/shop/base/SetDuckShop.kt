package jetbrains.kotlin.course.duck.shop.base

import jetbrains.kotlin.course.duck.shop.duck.DuckModel

// TODO: can I do it better?
class SetDuckShop(ducksNumber: Int) : ListOrSetDuckShop() {
    override val ducks: MutableSet<DuckModel> =
        initDucks(ducksNumber) { ducksNumber ->
            val generatedDucks = mutableSetOf<DuckModel>()
            do {
                val duck = DuckModel.generateDuck()
                if (duck !in generatedDucks) {
                    generatedDucks.add(duck)
                }
            } while (generatedDucks.size < ducksNumber)
            generatedDucks
        }
}
