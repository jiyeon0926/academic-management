package com.kotlin.academic.domain.department.dto

import com.kotlin.academic.domain.department.entity.Department

data class DepartmentResDto(
    val id: Long?,
    val code: String,
    val name: String) {

    constructor(department: Department) : this(
        id = department.id,
        code = department.code,
        name = department.name
    )
}