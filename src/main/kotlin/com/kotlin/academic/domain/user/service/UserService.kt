package com.kotlin.academic.domain.user.service

import com.kotlin.academic.domain.user.dto.UserResDto
import com.kotlin.academic.domain.user.repository.UserRepository
import com.kotlin.academic.global.error.CustomException
import com.kotlin.academic.global.error.ErrorCode
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(private val userRepository: UserRepository) {

    @Transactional(readOnly = true)
    fun findUserById(id: Long): UserResDto {
        val user = userRepository.findById(id).orElseThrow {
            throw CustomException(ErrorCode.USER_NOT_FOUND)
        }

        return UserResDto(user)
    }
}