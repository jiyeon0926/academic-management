package com.kotlin.academic

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AcademicApplication

fun main(args: Array<String>) {
	runApplication<AcademicApplication>(*args)
}
