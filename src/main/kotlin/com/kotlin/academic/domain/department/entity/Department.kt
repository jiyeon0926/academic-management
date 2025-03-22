package com.kotlin.academic.domain.department.entity

import com.kotlin.academic.domain.subject.entity.Subject
import com.kotlin.academic.domain.user.entity.User
import com.kotlin.academic.global.common.entity.BaseEntity
import jakarta.persistence.*

@Entity
class Department(code: String, name: String) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(unique = true)
    var code: String = code

    @Column(length = 30)
    var name: String = name

    @OneToMany(
        targetEntity = User::class,
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL])
    @JoinColumn(name = "department_id")
    var users: MutableList<User> = mutableListOf()

    @OneToMany(
        targetEntity = Subject::class,
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL])
    @JoinColumn(name = "department_id")
    var subjects: MutableList<Subject> = mutableListOf()
}