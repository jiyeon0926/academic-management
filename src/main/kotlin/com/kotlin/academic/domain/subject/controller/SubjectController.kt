package com.kotlin.academic.domain.subject.controller

import com.kotlin.academic.domain.subject.dto.SubjectListResDto
import com.kotlin.academic.domain.subject.dto.SubjectResDto
import com.kotlin.academic.domain.subject.service.SubjectService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/subjects")
class SubjectController(private val subjectService: SubjectService) {

    // 과목 전체 조회
    @GetMapping
    fun findAll(): List<SubjectListResDto> {
        return subjectService.findAll()
    }

    // 과목 단 건 조회
    @GetMapping("/{subjectId}")
    fun findSubjectById(@PathVariable subjectId: Long): SubjectResDto {
        return subjectService.findSubjectById(subjectId)
    }
}