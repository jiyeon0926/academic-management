package com.kotlin.academic.domain.subject.dto

import com.kotlin.academic.domain.subject.entity.Subject
import java.time.LocalDateTime
import java.time.LocalTime

data class SubjectResDto(
    val id: Long?,
    val departmentName: String,
    val professorName: String,
    val subjectName: String,
    val credits: Int,
    val subjectType: String,
    val maxCapacity: Int,
    val weekDay: String,
    val openAt: LocalTime,
    val closeAt: LocalTime,
    val createdAt: LocalDateTime,
    val updateAt: LocalDateTime) {

    constructor(subject: Subject) : this(
        id = subject.id,
        departmentName = subject.department.name,
        professorName = subject.professor.name,
        subjectName = subject.name,
        credits = subject.credits,
        subjectType = subject.type.name,
        maxCapacity = subject.maxCapacity,
        weekDay = subject.weekDay.name,
        openAt = subject.openAt,
        closeAt = subject.closeAt,
        createdAt = subject.createdAt,
        updateAt = subject.updatedAt
    )
}