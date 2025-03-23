package com.kotlin.academic.domain.course.service

import com.kotlin.academic.domain.course.dto.EnrollmentListResDto
import com.kotlin.academic.domain.course.entity.Course
import com.kotlin.academic.domain.course.repository.CourseRepository
import com.kotlin.academic.domain.subject.repository.SubjectRepository
import com.kotlin.academic.domain.user.repository.UserRepository
import com.kotlin.academic.global.error.CustomException
import com.kotlin.academic.global.error.ErrorCode
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CourseService(
    private val courseRepository: CourseRepository,
    private val userRepository: UserRepository,
    private val subjectRepository: SubjectRepository) {

    @Transactional(readOnly = true)
    fun findAllByStudent(studentId: Long): List<EnrollmentListResDto> {
        // 임시 유저, 수정 예정
        val student = userRepository.findById(1L).orElseThrow {
            throw CustomException(ErrorCode.USER_NOT_FOUND)
        }

        val courses = courseRepository.findAllByStudent(student)

        return courses.map { course -> EnrollmentListResDto(course) }
    }

    @Transactional
    fun enrollment(subjectId: Long?) {
        val validSubjectId = subjectId ?: throw CustomException(ErrorCode.REQUIRED_FIELD_MISSING)

        // 임시 유저, 수정 예정
        val student = userRepository.findById(1L).orElseThrow {
            throw CustomException(ErrorCode.USER_NOT_FOUND)
        }

        val subject = subjectRepository.findById(validSubjectId).orElseThrow {
            throw CustomException(ErrorCode.SUBJECT_NOT_FOUND)
        }

        val course = Course(student, subject)
        courseRepository.save(course)
    }

    @Transactional
    fun cancelEnrollment(studentId: Long, enrollmentId: Long) {
        // 임시 유저, 수정 예정
        val student = userRepository.findById(1L).orElseThrow {
            throw CustomException(ErrorCode.USER_NOT_FOUND)
        }

        val course = courseRepository.findCourseByIdAndStudent(enrollmentId, student).orElseThrow {
            throw CustomException(ErrorCode.COURSE_NOT_FOUND)
        }
        courseRepository.delete(course)
    }
}