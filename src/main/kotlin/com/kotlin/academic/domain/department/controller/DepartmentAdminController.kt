package com.kotlin.academic.domain.department.controller

import com.kotlin.academic.domain.department.dto.DepartmentResDto
import com.kotlin.academic.domain.department.service.DepartmentService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admins/departments")
class DepartmentAdminController(private val departmentService: DepartmentService) {

    // 학과 단 건 조회
    @GetMapping("/{departmentId}")
    fun findDepartmentById(@PathVariable departmentId: Long): DepartmentResDto {
        return  departmentService.findDepartmentById(departmentId)
    }
}