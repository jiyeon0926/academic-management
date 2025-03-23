package com.kotlin.academic.domain.department.controller

import com.kotlin.academic.domain.department.dto.DepartmentListResDto
import com.kotlin.academic.domain.department.service.DepartmentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/departments")
class DepartmentController(private val departmentService: DepartmentService) {

    // 학과 전체 조회
    @GetMapping
    fun findAll(): ResponseEntity<List<DepartmentListResDto>> {
        val all = departmentService.findAll()

        return ResponseEntity(all, HttpStatus.OK)
    }
}