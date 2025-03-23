package com.kotlin.academic.domain.subject.dto

import com.kotlin.academic.domain.department.entity.Department
import com.kotlin.academic.domain.subject.entity.Subject
import java.time.LocalTime

data class CreateSubjectReqDto(
    val departmentCode: String,
    val name: String,
    val credits: Int,
    val type: String,
    val maxCapacity: Int,
    val weekDay: String,
    val openAt: LocalTime,
    val closeAt: LocalTime) {

    constructor(department: Department, subject: Subject) : this(
        departmentCode = department.code,
        name = subject.name,
        credits = subject.credits,
        type = subject.type.name,
        maxCapacity = subject.maxCapacity,
        weekDay = subject.weekDay.name,
        openAt = subject.openAt,
        closeAt = subject.closeAt
    )
}