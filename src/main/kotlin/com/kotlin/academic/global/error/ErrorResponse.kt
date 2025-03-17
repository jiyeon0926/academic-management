package com.kotlin.academic.global.error

import java.time.LocalDateTime

data class ErrorResponse(
    val timestamp: LocalDateTime,
    val status: Int,
    val message: String) {

    constructor(status: Int, message: String) : this(
        LocalDateTime.now(),
        status,
        message
    )
}