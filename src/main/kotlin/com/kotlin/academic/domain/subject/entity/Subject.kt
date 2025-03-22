package com.kotlin.academic.domain.subject.entity

import com.kotlin.academic.domain.course.entity.Course
import com.kotlin.academic.domain.department.entity.Department
import com.kotlin.academic.domain.user.entity.User
import com.kotlin.academic.global.common.entity.BaseEntity
import com.kotlin.academic.global.constant.SubjectType
import com.kotlin.academic.global.constant.WeekDay
import jakarta.persistence.*
import java.time.LocalTime

@Entity
class Subject(
    department: Department,
    professor: User,
    name: String,
    credits: Int,
    type: String,
    maxCapacity: Int,
    weekDay: String,
    openAt: LocalTime,
    closeAt: LocalTime) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    var department: Department = department

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id", nullable = false)
    var professor: User = professor

    @Column(length = 30)
    var name: String = name

    var credits: Int = credits

    @Column(name = "subject_type", length = 10)
    @Enumerated(value = EnumType.STRING)
    var type: SubjectType = SubjectType.valueOf(type)

    var maxCapacity: Int = maxCapacity

    @Column(length = 3)
    @Enumerated(value = EnumType.STRING)
    var weekDay: WeekDay = WeekDay.valueOf(weekDay)

    var openAt: LocalTime = openAt

    var closeAt: LocalTime = closeAt

    @OneToMany(
        mappedBy = "subject",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL])
    @JoinColumn(name = "subject_id")
    var courses: MutableList<Course> = mutableListOf()
}