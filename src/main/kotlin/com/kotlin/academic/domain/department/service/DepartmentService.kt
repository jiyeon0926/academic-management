package com.kotlin.academic.domain.department.service

import com.kotlin.academic.domain.department.dto.DepartmentListResDto
import com.kotlin.academic.domain.department.dto.DepartmentResDto
import com.kotlin.academic.domain.department.repository.DepartmentRepository
import com.kotlin.academic.global.error.CustomException
import com.kotlin.academic.global.error.ErrorCode
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DepartmentService(private val departmentRepository: DepartmentRepository) {

    @Transactional(readOnly = true)
    fun findDepartmentById(id: Long): DepartmentResDto {
        val department = departmentRepository.findById(id).orElseThrow {
            throw CustomException(ErrorCode.DEPARTMENT_NOT_FOUND)
        }

        return DepartmentResDto(department)
    }

    @Transactional(readOnly = true)
    fun findAll(): List<DepartmentListResDto> {
        val departments = departmentRepository.findAll()

        return departments.map { department -> DepartmentListResDto(department.name) }
    }
}