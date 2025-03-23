package com.kotlin.academic.domain.department.repository

import com.kotlin.academic.domain.department.entity.Department
import mu.KotlinLogging
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import kotlin.test.Test

private val logger = KotlinLogging.logger { }

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DepartmentRepositoryTest(@Autowired val departmentRepository: DepartmentRepository) {

    val DATA_SIZE = 5

    private fun createDepartment(n: Int): Department {
        val formattedNumber = String.format("%02d", n)

        val department = Department(
            code = formattedNumber,
            name = "학과 $n"
        )

        return department
    }

    @BeforeAll
    fun beforeAll() {
        logger.info { "데이터 초기화 이전 조회 시작" }
        val beforeInitialize = departmentRepository.findAll()
        assertThat(beforeInitialize).hasSize(0)
        logger.info { "데이터 초기화 이전 조회 종료" }

        logger.info { "테스트 데이터 초기화 시작" }
        for (i in 1..DATA_SIZE) {
            val department = createDepartment(i)
            departmentRepository.save(department)
        }
        logger.info { "테스트 데이터 초기화 종료" }
    }

    @Test
    fun testFindAll() {
        logger.info { "findAll 테스트 시작" }
        val departments = departmentRepository.findAll()
        assertThat(departments).hasSize(DATA_SIZE)
        logger.info { "departments.size: ${departments.size}" }
        logger.info { "findAll 테스트 종료" }
    }

    @Test
    fun testFindById() {
        logger.info { "findById 테스트 시작" }
        val department = departmentRepository.findById(1).get()
        logger.info { department.code }
        logger.info { department.name }
        logger.info { "findById 테스트 종료" }
    }

    @Test
    fun testFindDepartmentByCode() {
        logger.info { "FindDepartmentByCode 테스트 시작" }
        val department = departmentRepository.findDepartmentByCode("01").get()
        logger.info { department.code }
        logger.info { department.name }
        logger.info { "FindDepartmentByCode 테스트 종료" }
    }
}