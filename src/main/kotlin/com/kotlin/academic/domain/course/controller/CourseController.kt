package com.kotlin.academic.domain.course.controller

import com.kotlin.academic.domain.course.dto.EnrollmentListResDto
import com.kotlin.academic.domain.course.dto.EnrollmentReqDto
import com.kotlin.academic.domain.course.service.CourseService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/students")
class CourseController(private val courseService: CourseService) {

    // 수강 목록 조회
    @GetMapping("/{studentId}/enrollments")
    fun findAll(): List<EnrollmentListResDto> {
        return courseService.findAll()
    }

    // 수강 신청
    @PostMapping("/enrollments")
    fun enrollment(@RequestBody enrollmentReqDto: EnrollmentReqDto): ResponseEntity<String> {
        courseService.enrollment(enrollmentReqDto.subjectId)

        return ResponseEntity("수강 신청 완료", HttpStatus.OK)
    }

    // 수강 취소
    @DeleteMapping("/{studentId}/enrollments/{enrollmentId}")
    fun cancelEnrollment(@PathVariable studentId: Long,
                         @PathVariable enrollmentId: Long): ResponseEntity<String> {
        courseService.cancelEnrollment(studentId, enrollmentId)

        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}