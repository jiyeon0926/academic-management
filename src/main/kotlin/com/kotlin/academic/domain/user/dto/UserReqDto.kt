package com.kotlin.academic.domain.user.dto

import com.kotlin.academic.domain.department.entity.Department
import java.time.Year

data class UserReqDto(
    val department: Department?,
    val name: String,
    val loginId: String,
    val password: String,
    val academicYear: Year?,
    val role: String) {
}