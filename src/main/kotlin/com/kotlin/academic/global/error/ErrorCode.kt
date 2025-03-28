package com.kotlin.academic.global.error

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val status: HttpStatus,
    val message: String) {

    // 400
    REQUIRED_FIELD_MISSING(HttpStatus.BAD_REQUEST, "필수값을 입력해주세요."),

    // 404 NOT_FOUND
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),
    DEPARTMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "학과를 찾을 수 없습니다."),
    SUBJECT_NOT_FOUND(HttpStatus.NOT_FOUND, "과목를 찾을 수 없습니다."),
    COURSE_NOT_FOUND(HttpStatus.NOT_FOUND, "수강 신청 내역이 없습니다.")
}