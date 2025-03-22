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

    private fun createDepartment(): Department {
        val department = Department(
            code = "01",
            name = "컴퓨터학과"
        )

        return department
    }

    private fun createdStudent(n: Int, department: Department): User {
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

    @BeforeAll
    fun beforeAll() {
        logger.info { "데이터 초기화 이전 조회 시작" }
        val beforeInitialize = userRepository.findAll()
        assertThat(beforeInitialize).hasSize(0)
        logger.info { "데이터 초기화 이전 조회 종료" }

        logger.info { "테스트 데이터 초기화 시작" }
        val department = createDepartment()
        departmentRepository.save(department)

        for (i in 1..DATA_SIZE) {
            val students = createdStudent(i, department)
            userRepository.save(students)
        }
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
}