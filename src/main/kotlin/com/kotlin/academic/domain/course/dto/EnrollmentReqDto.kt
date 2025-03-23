package com.kotlin.academic.domain.course.dto

import com.kotlin.academic.domain.subject.entity.Subject

data class EnrollmentReqDto(val subjectId: Long?) {

    constructor(subject: Subject) : this(subjectId = subject.id)
}