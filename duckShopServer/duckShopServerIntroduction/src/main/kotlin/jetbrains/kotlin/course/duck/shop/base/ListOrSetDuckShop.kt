package jetbrains.kotlin.course.duck.shop.base

import jetbrains.kotlin.course.duck.shop.duck.DuckAccessuare
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

    fun filterByAccessuare(accessuare: DuckAccessuare) = ducks.filter { accessuare in it.accessuares }

    fun shuffle() = ducks.shuffled()

    fun countDucksByColorAndAccessuare(color: DuckColor, accessuare: DuckAccessuare): Int = run {
        val (with, _) = ducks.partition { it.color == color && accessuare in it.accessuares }
        with.size
    }
}