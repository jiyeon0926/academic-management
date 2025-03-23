package com.kotlin.academic.domain.department.repository

import com.kotlin.academic.domain.department.entity.Department
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface DepartmentRepository : JpaRepository<Department, Long> {

    fun findDepartmentByCode(code: String): Optional<Department>
}