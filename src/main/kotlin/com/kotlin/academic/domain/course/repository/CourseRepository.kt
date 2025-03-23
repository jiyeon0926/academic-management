package com.kotlin.academic.domain.course.repository

import com.kotlin.academic.domain.course.entity.Course
import com.kotlin.academic.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CourseRepository : JpaRepository<Course, Long> {

    fun findAllByStudent(student: User): List<Course>

    fun findCourseByIdAndStudent(id: Long, student: User): Optional<Course>
}