package org.jetbrains.kotlin.course.old.school

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OldSchoolApplication

@Suppress("SpreadOperator")
fun main(args: Array<String>) {
    runApplication<OldSchoolApplication>(*args)
}

