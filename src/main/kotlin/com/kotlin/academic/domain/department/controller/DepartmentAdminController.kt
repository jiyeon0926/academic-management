package com.kotlin.academic.domain.department.controller

import com.kotlin.academic.domain.department.dto.CreateDepartmentReqDto
import com.kotlin.academic.domain.department.dto.DepartmentResDto
import com.kotlin.academic.domain.department.dto.UpdateDepartmentReqDto
import com.kotlin.academic.domain.department.service.DepartmentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admins/departments")
class DepartmentAdminController(private val departmentService: DepartmentService) {

    // 학과 단 건 조회
    @GetMapping("/{departmentId}")
    fun findDepartmentById(@PathVariable departmentId: Long): ResponseEntity<DepartmentResDto> {
        val departmentResDto = departmentService.findDepartmentById(departmentId)

        return ResponseEntity(departmentResDto, HttpStatus.OK)
    }

    // 학과 등록
    @PostMapping
    fun createDepartment(@RequestBody createDepartmentReqDto: CreateDepartmentReqDto): ResponseEntity<DepartmentResDto> {
        val departmentResDto = departmentService.createDepartment(createDepartmentReqDto.code, createDepartmentReqDto.name)

        return ResponseEntity(departmentResDto, HttpStatus.CREATED)
    }

    // 학과명 수정
    @PatchMapping("/{departmentId}")
    fun updateDepartment(@PathVariable departmentId: Long,
                         @RequestBody updateDepartmentReqDto: UpdateDepartmentReqDto): ResponseEntity<DepartmentResDto> {
        val departmentResDto = departmentService.updateDepartment(departmentId, updateDepartmentReqDto.name)

        return ResponseEntity(departmentResDto, HttpStatus.OK)
    }

    // 학과 삭제
    @DeleteMapping("/{departmentId}")
    fun deleteDepartment(@PathVariable departmentId: Long): ResponseEntity<Void> {
        departmentService.deleteDepartment(departmentId)

        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}