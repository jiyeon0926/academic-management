package com.kotlin.academic.domain.department.repository

import com.kotlin.academic.domain.department.entity.Department
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DepartmentRepository : JpaRepository<Department, Long> {
}