package org.jetbrains.kotlin.course.duck.shop

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DuckShopApplication

@Suppress("SpreadOperator")
fun main(args: Array<String>) {
    runApplication<DuckShopApplication>(*args)
}

