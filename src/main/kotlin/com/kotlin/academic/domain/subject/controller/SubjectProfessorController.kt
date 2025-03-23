package com.kotlin.academic.domain.subject.controller

import com.kotlin.academic.domain.subject.dto.CreateSubjectReqDto
import com.kotlin.academic.domain.subject.dto.SubjectResDto
import com.kotlin.academic.domain.subject.dto.UpdateSubjectReqDto
import com.kotlin.academic.domain.subject.service.SubjectService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/professors/subjects")
class SubjectProfessorController(private val subjectService: SubjectService) {

    // 과목 등록
    @PostMapping
    fun createSubject(@RequestBody createSubjectReqDto: CreateSubjectReqDto): ResponseEntity<SubjectResDto> {
        val subjectResDto = subjectService.createSubject(
            createSubjectReqDto.departmentCode,
            createSubjectReqDto.name,
            createSubjectReqDto.credits,
            createSubjectReqDto.type,
            createSubjectReqDto.maxCapacity,
            createSubjectReqDto.weekDay,
            createSubjectReqDto.openAt,
            createSubjectReqDto.closeAt
        )

        return ResponseEntity(subjectResDto, HttpStatus.CREATED)
    }

    // 과목 수정
    @PatchMapping("/{subjectId}")
    fun updateSubject(@PathVariable subjectId: Long,
                      @RequestBody updateSubjectReqDto: UpdateSubjectReqDto): ResponseEntity<SubjectResDto> {
        val subjectResDto = subjectService.updateSubject(
            subjectId,
            updateSubjectReqDto.name,
            updateSubjectReqDto.maxCapacity,
            updateSubjectReqDto.weekDay,
            updateSubjectReqDto.openAt,
            updateSubjectReqDto.closeAt
        )

        return ResponseEntity(subjectResDto, HttpStatus.OK)
    }

    // 과목 삭제
    @DeleteMapping("/{subjectId}")
    fun deleteSubject(@PathVariable subjectId: Long): ResponseEntity<Void> {
        subjectService.deleteSubject(subjectId)

        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}