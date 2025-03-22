package com.kotlin.academic.domain.department.dto

import com.kotlin.academic.domain.department.entity.Department

data class DepartmentListResDto(val name: String) {

    constructor(department: Department) : this(
        name = department.name
    )
}