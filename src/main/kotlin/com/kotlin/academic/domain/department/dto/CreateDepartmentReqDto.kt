package com.kotlin.academic.domain.department.dto

import com.kotlin.academic.domain.department.entity.Department

data class CreateDepartmentReqDto(val code: String, val name: String) {

    constructor(department: Department) : this(
        code = department.code,
        name = department.name
    )
}