package com.kotlin.academic.domain.user.dto

import com.kotlin.academic.domain.user.entity.User
import com.kotlin.academic.global.constant.UserRole

data class UserResDto(
    val id: Long?,
    val name: String,
    val code: String?,
    val role: UserRole) {

    constructor(user: User) : this(
        id = user.id,
        name = user.name,
        code = user.code,
        role = user.role
    )
}