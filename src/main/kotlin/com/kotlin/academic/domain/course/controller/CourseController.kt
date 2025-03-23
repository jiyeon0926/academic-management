package com.kotlin.academic.domain.course.controller

import com.kotlin.academic.domain.course.dto.EnrollmentListResDto
import com.kotlin.academic.domain.course.service.CourseService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/students")
class CourseController(private val courseService: CourseService) {

    // 수강 목록 조회
    @GetMapping("/{studentId}/enrollments")
    fun findAll(): List<EnrollmentListResDto> {
        return courseService.findAll()
    }
}