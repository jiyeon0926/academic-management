package com.kotlin.academic.domain.user.service

import com.kotlin.academic.domain.user.dto.UserResDto
import com.kotlin.academic.domain.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.nio.file.attribute.UserPrincipalNotFoundException

@Service
class UserService(private val userRepository: UserRepository) {

    @Transactional(readOnly = true)
    fun getUserById(id: Long): UserResDto {
        val user = userRepository.findById(id).orElseThrow { UserPrincipalNotFoundException("사용자를 찾을 수 없습니다.") }
        return UserResDto(user)
    }
}