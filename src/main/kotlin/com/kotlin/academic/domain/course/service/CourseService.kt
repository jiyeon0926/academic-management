package com.kotlin.academic.domain.course.service

import com.kotlin.academic.domain.course.dto.EnrollmentListResDto
import com.kotlin.academic.domain.course.repository.CourseRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CourseService(private val courseRepository: CourseRepository) {

    @Transactional(readOnly = true)
    fun findAll(): List<EnrollmentListResDto> {
        val courses = courseRepository.findAll()

        return courses.map { course -> EnrollmentListResDto(course) }
    }
}