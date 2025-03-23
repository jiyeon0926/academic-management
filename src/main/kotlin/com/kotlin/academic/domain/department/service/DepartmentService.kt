package com.kotlin.academic.domain.department.service

import com.kotlin.academic.domain.department.dto.DepartmentListResDto
import com.kotlin.academic.domain.department.dto.DepartmentResDto
import com.kotlin.academic.domain.department.entity.Department
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

    @Transactional
    fun createDepartment(code: String, name: String): DepartmentResDto {
        val formattedCode = code.padStart(2, '0')
        val department = Department(
            code = formattedCode,
            name = name
        )
        departmentRepository.save(department)

        return DepartmentResDto(department)
    }

    @Transactional
    fun updateDepartment(id: Long, name: String): DepartmentResDto {
        val department = departmentRepository.findById(id).orElseThrow {
            throw CustomException(ErrorCode.DEPARTMENT_NOT_FOUND)
        }

        department.updateName(name)

        return DepartmentResDto(department)
    }

    @Transactional
    fun deleteDepartment(id: Long) {
        val department = departmentRepository.findById(id).orElseThrow {
            throw CustomException(ErrorCode.DEPARTMENT_NOT_FOUND)
        }

        departmentRepository.delete(department)
    }
}