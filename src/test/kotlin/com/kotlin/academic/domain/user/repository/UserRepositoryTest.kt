package com.kotlin.academic.domain.user.repository

import com.kotlin.academic.domain.department.entity.Department
import com.kotlin.academic.domain.department.repository.DepartmentRepository
import com.kotlin.academic.domain.user.entity.User
import com.kotlin.academic.global.constant.UserRole
import mu.KotlinLogging
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.time.Year

private val logger = KotlinLogging.logger { }

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserRepositoryTest(
    @Autowired val userRepository: UserRepository,
    @Autowired val departmentRepository: DepartmentRepository) {

    val DATA_SIZE = 5
    val DATA_SIZE_TWO = 3

    private fun createDepartment(): Department {
        val department = Department(
            code = "01",
            name = "컴퓨터학과"
        )

        return department
    }

    private fun createStudent(n: Int, department: Department): User {
        val formattedNumber = n.toString().padStart(3, '0')

        val student = User(
            department = department,
            name = "이름 ${n}",
            loginId = "${n}",
            password = "${n}",
            code = "2025${department.code}$formattedNumber",
            academicYear = Year.now(),
            role = UserRole.STUDENT.name
        )

        return student
    }

    private fun createProfessor(n: Int, department: Department): User {
        val formattedNumber = n.toString().padStart(3, '0')

        val professor = User(
            department = department,
            name = "이름 ${n}",
            loginId = "p${n}",
            password = "${n}",
            code = "P2025${department.code}$formattedNumber",
            academicYear = Year.now(),
            role = UserRole.PROFESSOR.name
        )

        return professor
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

        val students = (1..DATA_SIZE).map { createStudent(it, department) }
        val professors = (1..DATA_SIZE_TWO).map { createProfessor(it, department) }

        userRepository.saveAll(students + professors)

        logger.info { "테스트 데이터 초기화 종료" }
    }

    @Test
    fun testFindAll() {
        logger.info { "findAll 테스트 시작" }
        val students = userRepository.findAll()
        assertThat(students).hasSize(DATA_SIZE)
        logger.info { "students.size: ${students.size}" }
        logger.info { "findAll 테스트 종료" }
    }

    @Test
    fun testFindById() {
        logger.info { "findById 테스트 시작" }
        val user = userRepository.findById(1).get()
        logger.info { user.name }
        logger.info { user.loginId }
        logger.info { user.code }
        logger.info { user.academicYear }
        logger.info { "findById 테스트 종료" }
    }

    @Test
    fun testCountByDepartment() {
        logger.info { "countByDepartment 테스트 시작" }
        val department = departmentRepository.findAll().firstOrNull()
            ?: throw IllegalStateException("데이터가 없습니다.")
        val count = userRepository.countByDepartment(department)

        logger.info { "department: ${department.name}, 학생 수: $count" }
        assertThat(count).isEqualTo(DATA_SIZE.toLong())
        logger.info { "countByDepartment 테스트 종료" }
    }

    @Test
    fun testCountByDepartmentAndRole() {
        logger.info { "countByDepartmentAndRole 테스트 시작" }
        val department = departmentRepository.findAll().firstOrNull()
            ?: throw IllegalStateException("데이터가 없습니다.")

        val studentCount = userRepository.countByDepartmentAndRole(department, UserRole.STUDENT)
        logger.info { "department: ${department.name}, 학생 수: $studentCount" }
        assertThat(studentCount).isEqualTo(DATA_SIZE.toLong())

        val professorCount = userRepository.countByDepartmentAndRole(department, UserRole.PROFESSOR)
        logger.info { "department: ${department.name}, 교수 수: $professorCount" }
        assertThat(professorCount).isEqualTo(DATA_SIZE_TWO.toLong())

        logger.info { "countByDepartmentAndRole 테스트 종료" }
    }
}