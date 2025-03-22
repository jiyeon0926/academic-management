package com.kotlin.academic.domain.course.dto

import com.kotlin.academic.domain.course.entity.Course
import java.time.LocalDateTime

data class EnrollmentListResDto(
    val id: Long?,
    val subjectName: String,
    val credits: Int,
    val subjectType: String,
    val createdAt: LocalDateTime) {

    constructor(course: Course) : this(
        id = course.id,
        subjectName = course.subject.name,
        credits = course.subject.credits,
        subjectType = course.subject.type.name,
        createdAt = course.createdAt
    )
}