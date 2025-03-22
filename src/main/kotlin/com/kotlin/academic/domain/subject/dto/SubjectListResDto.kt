package com.kotlin.academic.domain.subject.dto

import com.kotlin.academic.domain.subject.entity.Subject

data class SubjectListResDto(
    val id: Long?,
    val departmentName: String,
    val professorName: String,
    val subjectName: String,
    val credits: Int,
    val subjectType: String,
    val maxCapacity: Int) {

    constructor(subject: Subject) : this(
        id = subject.id,
        departmentName = subject.department.name,
        professorName = subject.professor.name,
        subjectName = subject.name,
        credits = subject.credits,
        subjectType = subject.type.name,
        maxCapacity = subject.maxCapacity
    )
}