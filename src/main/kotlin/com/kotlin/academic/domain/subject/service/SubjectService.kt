package com.kotlin.academic.domain.subject.service

import com.kotlin.academic.domain.department.repository.DepartmentRepository
import com.kotlin.academic.domain.subject.dto.SubjectListResDto
import com.kotlin.academic.domain.subject.dto.SubjectResDto
import com.kotlin.academic.domain.subject.entity.Subject
import com.kotlin.academic.domain.subject.repository.SubjectRepository
import com.kotlin.academic.domain.user.repository.UserRepository
import com.kotlin.academic.global.error.CustomException
import com.kotlin.academic.global.error.ErrorCode
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalTime

@Service
class SubjectService(
    private val subjectRepository: SubjectRepository,
    private val departmentRepository: DepartmentRepository,
    private val userRepository: UserRepository) {

    @Transactional(readOnly = true)
    fun findAll(): List<SubjectListResDto> {
        val subjects = subjectRepository.findAll()

        return subjects.map { subject -> SubjectListResDto(subject) }
    }

    @Transactional(readOnly = true)
    fun findSubjectById(id: Long): SubjectResDto {
        val subject = subjectRepository.findById(id).orElseThrow {
            throw CustomException(ErrorCode.SUBJECT_NOT_FOUND)
        }

        return SubjectResDto(subject)
    }

    @Transactional
    fun createSubject(departmentCode: String,
                      name: String,
                      credits: Int,
                      type: String,
                      maxCapacity: Int,
                      weekDay: String,
                      openAt: LocalTime,
                      closeAt: LocalTime): SubjectResDto {
        val department = departmentRepository.findDepartmentByCode(departmentCode).orElseThrow {
            throw CustomException(ErrorCode.DEPARTMENT_NOT_FOUND)
        }

        // 임시 유저, 수정 예정
        val professor = userRepository.findById(1L).orElseThrow {
            throw CustomException(ErrorCode.USER_NOT_FOUND)
        }

        val subject = Subject(
            department,
            professor,
            name,
            credits,
            type,
            maxCapacity,
            weekDay.uppercase(),
            openAt,
            closeAt
        )
        val savedSubject = subjectRepository.save(subject)

        return SubjectResDto(savedSubject)
    }

    @Transactional
    fun updateSubject(id: Long,
                      name: String,
                      maxCapacity: Int,
                      weekDay: String,
                      openAt: LocalTime,
                      closeAt: LocalTime): SubjectResDto {
        val subject = subjectRepository.findById(id).orElseThrow {
            throw CustomException(ErrorCode.SUBJECT_NOT_FOUND)
        }

        subject.updateSubject(name, maxCapacity, weekDay, openAt, closeAt)

        return SubjectResDto(subject)
    }

    @Transactional
    fun deleteSubject(id: Long) {
        val subject = subjectRepository.findById(id).orElseThrow {
            throw CustomException(ErrorCode.SUBJECT_NOT_FOUND)
        }

        subjectRepository.delete(subject)
    }
}