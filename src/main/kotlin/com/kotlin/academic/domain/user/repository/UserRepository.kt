package com.kotlin.academic.domain.user.repository

import com.kotlin.academic.domain.user.dto.UserResDto
import com.kotlin.academic.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
}