package jetbrains.kotlin.course.duck.shop.map

import jetbrains.kotlin.course.duck.shop.duck.DuckModel
import jetbrains.kotlin.course.duck.shop.util.GameSettings

class MapDuckShop(ducksNumber: Int) {
    val duckToPrice: MutableMap<DuckModel, Int> = initDucksWithPrice(ducksNumber)

    private fun initDucksWithPrice(ducksNumber: Int): MutableMap<DuckModel, Int> {
        require(ducksNumber <= GameSettings.MAX_DUCKS_NUMBER) { "The number of ducks must be not grater than ${GameSettings.MAX_DUCKS_NUMBER}" }
        val generatedDucks = mutableMapOf<DuckModel, Int>()
        do {
            val duck = DuckModel.generateDuck()
            if (duck !in generatedDucks.keys) {
                // TODO: Generate random number
                generatedDucks[duck] = GameSettings.MAX_DUCK_PRICE
            }
        } while (generatedDucks.size < ducksNumber)
        return generatedDucks
    }

    fun increasePriceForAll(diff: Int) = run {
        duckToPrice.replaceAll { _, price -> price + diff }
        duckToPrice
    }
}