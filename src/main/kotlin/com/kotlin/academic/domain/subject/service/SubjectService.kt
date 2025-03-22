package com.kotlin.academic.domain.subject.service

import com.kotlin.academic.domain.subject.dto.SubjectListResDto
import com.kotlin.academic.domain.subject.dto.SubjectResDto
import com.kotlin.academic.domain.subject.repository.SubjectRepository
import com.kotlin.academic.global.error.CustomException
import com.kotlin.academic.global.error.ErrorCode
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SubjectService(private val subjectRepository: SubjectRepository) {

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
}