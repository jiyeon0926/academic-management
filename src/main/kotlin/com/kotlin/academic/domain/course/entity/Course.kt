package com.kotlin.academic.domain.course.entity

import com.kotlin.academic.domain.subject.entity.Subject
import com.kotlin.academic.domain.user.entity.User
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime

@Entity
@Table(name = "course_enrollment")
class Course(student: User, subject: Subject) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne(targetEntity = User::class, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    var student: User = student

    @ManyToOne(targetEntity = Subject::class, fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    var subject: Subject = subject

    @CreatedDate
    @Column(nullable = false, updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()
}