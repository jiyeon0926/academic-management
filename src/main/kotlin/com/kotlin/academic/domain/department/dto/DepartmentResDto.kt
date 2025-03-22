package com.kotlin.academic.domain.department.dto

import com.kotlin.academic.domain.department.entity.Department
import java.time.LocalDateTime

data class DepartmentResDto(
    val id: Long?,
    val code: String,
    val name: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime) {

    constructor(department: Department) : this(
        id = department.id,
        code = department.code,
        name = department.name,
        createdAt = department.createdAt,
        updatedAt = department.updatedAt
    )
}