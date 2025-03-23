package com.kotlin.academic.domain.subject.dto

import com.kotlin.academic.domain.subject.entity.Subject
import java.time.LocalTime

data class UpdateSubjectReqDto(
    val name: String,
    val maxCapacity: Int,
    val weekDay: String,
    val openAt: LocalTime,
    val closeAt: LocalTime) {

    constructor(subject: Subject) : this(
        name = subject.name,
        maxCapacity = subject.maxCapacity,
        weekDay = subject.weekDay.name,
        openAt = subject.openAt,
        closeAt = subject.closeAt
    )
}