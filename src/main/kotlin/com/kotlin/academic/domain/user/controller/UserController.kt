package com.kotlin.academic.domain.user.controller

import com.kotlin.academic.domain.user.dto.UserResDto
import com.kotlin.academic.domain.user.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    // 프로필 조회
    @GetMapping("/{userId}")
    fun findUserById(@PathVariable userId: Long): UserResDto {
        return  userService.findUserById(userId)
    }
}