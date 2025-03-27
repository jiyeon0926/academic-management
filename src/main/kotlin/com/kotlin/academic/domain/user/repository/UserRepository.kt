package com.kotlin.academic.domain.user.repository

import com.kotlin.academic.domain.department.entity.Department
import com.kotlin.academic.domain.user.entity.User
import com.kotlin.academic.global.constant.UserRole
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {

    fun countByDepartment(department: Department): Long

    fun countByDepartmentAndRole(department: Department, role: UserRole): Long
}