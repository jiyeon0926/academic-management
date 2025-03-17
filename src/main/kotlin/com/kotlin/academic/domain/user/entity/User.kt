package com.kotlin.academic.domain.user.entity

import com.kotlin.academic.domain.course.entity.Course
import com.kotlin.academic.domain.department.entity.Department
import com.kotlin.academic.domain.subject.entity.Subject
import com.kotlin.academic.global.common.entity.BaseEntity
import com.kotlin.academic.global.constant.UserRole
import jakarta.persistence.*
import java.time.Year

@Entity
class User(
    department: Department?,
    name: String,
    loginId: String,
    password: String,
    code: String?,
    academicYear: Year?,
    role: String) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne(targetEntity = Department::class, fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = true)
    var department: Department? = department

    @Column(length = 10)
    var name: String = name

    @Column(length = 100, unique = true)
    var loginId: String = loginId

    @Column(length = 200)
    var password: String = password

    @Column(length = 20, unique = true)
    var code: String? = code

    var academicYear: Year? = academicYear

    @Column(length = 10)
    @Enumerated(value = EnumType.STRING)
    var role: UserRole = UserRole.valueOf(role)

    @OneToMany(
        targetEntity = Subject::class,
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL])
    @JoinColumn(name = "user_id")
    var subjects: MutableList<Subject> = mutableListOf()

    @OneToMany(
        targetEntity = Course::class,
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL])
    @JoinColumn(name = "user_id")
    var courses: MutableList<Course> = mutableListOf()
}