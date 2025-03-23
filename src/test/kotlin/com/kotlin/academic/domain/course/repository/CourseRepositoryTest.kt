package com.kotlin.academic.domain.course.repository

import com.kotlin.academic.domain.course.entity.Course
import com.kotlin.academic.domain.department.entity.Department
import com.kotlin.academic.domain.department.repository.DepartmentRepository
import com.kotlin.academic.domain.subject.entity.Subject
import com.kotlin.academic.domain.subject.repository.SubjectRepository
import com.kotlin.academic.domain.user.entity.User
import com.kotlin.academic.domain.user.repository.UserRepository
import com.kotlin.academic.global.constant.SubjectType
import com.kotlin.academic.global.constant.UserRole
import com.kotlin.academic.global.constant.WeekDay
import mu.KotlinLogging
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.time.LocalTime
import java.time.Year
import kotlin.test.Test

private val logger = KotlinLogging.logger { }

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CourseRepositoryTest(
    @Autowired val courseRepository: CourseRepository,
    @Autowired val userRepository: UserRepository,
    @Autowired val departmentRepository: DepartmentRepository,
    @Autowired val subjectRepository: SubjectRepository) {

    private fun createDepartment(): Department {
        val department = Department(
            code = "01",
            name = "컴퓨터학과"
        )

        return department
    }

    private fun createStudent(department: Department): User {
        val student = User(
            department = department,
            name = "학생",
            loginId = "학생1",
            password = "123",
            code = "2025${department.code}001",
            academicYear = Year.now(),
            role = UserRole.STUDENT.name
        )

        return student
    }

    private fun createProfessor(department: Department): User {
        val student = User(
            department = department,
            name = "교수",
            loginId = "교수1",
            password = "123",
            code = "P2025${department.code}001",
            academicYear = Year.now(),
            role = UserRole.PROFESSOR.name
        )

        return student
    }

    private fun createSubject(department: Department, professor: User): Subject {
        val subject = Subject(
            department = department,
            professor = professor,
            name = "자바프로그래밍",
            credits = 3,
            type = SubjectType.MAJOR.name,
            maxCapacity = 25,
            weekDay = WeekDay.MON.name,
            openAt = LocalTime.now(),
            closeAt = LocalTime.now()
        )

        return subject
    }

    private fun enrollment(student: User, subject: Subject): Course {
        val course = Course(student = student, subject = subject)

        return course
    }

    @BeforeAll
    fun beforeAll() {
        logger.info { "데이터 초기화 이전 조회 시작" }
        val beforeInitialize = userRepository.findAll()
        assertThat(beforeInitialize).hasSize(0)
        logger.info { "데이터 초기화 이전 조회 종료" }

        logger.info { "테스트 데이터 초기화 시작" }
        val department = createDepartment()
        departmentRepository.save(department)

        val student = createStudent(department)
        userRepository.save(student)

        val professor = createProfessor(department)
        userRepository.save(professor)

        val subject = createSubject(department, professor)
        subjectRepository.save(subject)

        val course = enrollment(student, subject)
        courseRepository.save(course)
        logger.info { "테스트 데이터 초기화 종료" }
    }

    @Test
    fun testFindCourseByIdAndStudent() {
        logger.info { "findCourseByIdAndStudent 테스트 시작" }
        val student = userRepository.findById(1L).get()
        val course = courseRepository.findCourseByIdAndStudent(1L, student)
        assertThat(course.student).isEqualTo(student)
        logger.info { "findCourseByIdAndStudent 테스트 종료" }
    }
}