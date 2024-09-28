package org.jetbrains.kotlin.course.culinary

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CulinaryApplication

@Suppress("SpreadOperator")
fun main(args: Array<String>) {
    runApplication<CulinaryApplication>(*args)
}
