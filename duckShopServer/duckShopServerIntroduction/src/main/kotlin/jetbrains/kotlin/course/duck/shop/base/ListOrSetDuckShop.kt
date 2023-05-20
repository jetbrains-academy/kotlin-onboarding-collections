package jetbrains.kotlin.course.duck.shop.base

import jetbrains.kotlin.course.duck.shop.duck.DuckAccessory
import jetbrains.kotlin.course.duck.shop.duck.DuckColor
import jetbrains.kotlin.course.duck.shop.duck.DuckModel
import jetbrains.kotlin.course.duck.shop.util.GameSettings

abstract class ListOrSetDuckShop {
    abstract val ducks: MutableCollection<DuckModel>

    protected fun <T: MutableCollection<DuckModel>> initDucks(ducksNumber: Int, duckInitializer: (Int) -> T): T {
        require(ducksNumber <= GameSettings.MAX_DUCKS_NUMBER) { "The number of ducks must be not grater than ${GameSettings.MAX_DUCKS_NUMBER}" }
        return duckInitializer(ducksNumber)
    }

    fun addDuck() = if (ducks.size == GameSettings.MAX_DUCKS_NUMBER) {
        null
    } else {
        DuckModel.generateDuck().also { ducks.add(it) }
    }

    fun removeDuckByColor(color: DuckColor) = ducks.removeIf { it.color == color }

    fun filterByAccessory(accessory: DuckAccessory) = ducks.filter { accessory in it.accessories }

    fun shuffle() = ducks.shuffled()

    fun countDucksByColorAndAccessory(color: DuckColor, accessory: DuckAccessory): Int = run {
        val (with, _) = ducks.partition { it.color == color && accessory in it.accessories }
        with.size
    }
}