package org.jetbrains.kotlin.course.duck.shop.base

import org.jetbrains.kotlin.course.duck.shop.duck.DuckModel

class ListDuckShop(ducksNumber: Int) : ListOrSetDuckShop() {
    override val ducks: MutableList<DuckModel> =
        initDucks(ducksNumber) { ducksNumber -> MutableList(ducksNumber) { DuckModel.generateDuck() } }

    fun sortByColor() = ducks.sortBy { it.color }

    fun sortByAccessoriesCount() = ducks.sortBy { it.accessories.size }
}
