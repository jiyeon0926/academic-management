package com.kotlin.academic.domain.subject.repository

import com.kotlin.academic.domain.subject.entity.Subject
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SubjectRepository : JpaRepository<Subject, Long> {
}