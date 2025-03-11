# 🏫 학사 관리 서비스
- 학생들이 전공 및 교양 과목을 수강 신청할 수 있습니다.
- 한 학기에 최대 18학점까지 수강할 수 있습니다.

# 💡 기능
<details>
  <summary><b>공통</b></summary>
  <div markdown="1">
    <p></p>
    <li>회원 가입을 할 수 있습니다.</li>
    <li>회원 탈퇴를 할 수 있습니다.</li>
    <li>비밀번호 변경을 할 수 있습니다.</li>
    <li>프로필을 조회할 수 있습니다.</li>
  </div>
</details>

<details>
  <summary><b>학생</b></summary>
  <div markdown="1">
    <p></p>
    <li>개설된 과목을 전체 조회할 수 있습니다.</li>
    <li>여러 과목을 수강 신청할 수 있습니다.</li>
    <li>수강 신청한 과목을 취소할 수 있습니다.</li>
    <li>수강 신청한 과목 및 신청한 과목의 총 학점을 조회할 수 있습니다.</li>
  </div>
</details>

<details>
  <summary><b>교수</b></summary>
  <div markdown="1">
    <p></p>
    <li>과목을 등록할 수 있습니다.</li>
    <li>과목명을 수정할 수 있습니다.</li>
    <li>과목 유형을 수정할 수 있습니다.</li>
    <li>수강 가능 인원을 수정할 수 있습니다.</li>
    <li>학점을 수정할 수 있습니다.</li>
    <li>강의 시간을 수정할 수 있습니다.</li>
    <li>요일을 수정할 수 있습니다.</li>
    <li>과목을 삭제할 수 있습니다.</li>
  </div>
</details>

<details>
  <summary><b>관리자</b></summary>
  <div markdown="1">
    <p></p>
    <li>학과를 등록합니다.</li>
    <li>학과 코드를 수정할 수 있습니다.</li>
    <li>학과명을 수정할 수 있습니다.</li>
    <li>학과를 삭제할 수 있습니다.</li>
  </div>
</details>

# 🧩 설계

## ERD
![academic-management](https://github.com/user-attachments/assets/1e1a2551-4e67-4e2c-9deb-6e3983d5577b)

### 1️⃣ 사용자
|id|department_id|name|login_id|password|code|academic_year|role|created_at|updated_at|
|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
|1|NULL|관리자|admin123|$2a$10$Mi5YA9IEJZngR7FDIjdPoecNm71JImr4D5tQMpGlZ0.QUxLKihT5e|NULL|NULL|ADMIN|2025-01-07 05:22:01.046133|2025-01-07 05:22:01.046133|
|2|1|파이썬|py123|$2a$10$Mi5YA9fijs8gR7fkCblPoecNm71JImr4D5tQMpGlZ0.QUxLKihT5e|202501001|2025|STUDENT|2025-02-07 05:22:01.046133|2025-02-08 05:22:01.046133|
|3|1|코틀린|kotlin123|$2a$10$Mi5YA9IEJZngR7fkCblPoecNm71J8fs9f5tQMpGlZ0.QUxLKihT5e|P202501001|2025|PROFESSOR|2025-02-09 05:22:01.046133|2025-02-09 05:22:01.046133|

### 2️⃣ 학과
|id|code|name|created_at|updated_at|
|:---:|:---:|:---:|:---:|:---:|
|1|01|컴퓨터학과|2025-01-07 11:22:01.046133|2025-01-07 11:22:01.046133|
|2|02|정보통신공학과|2025-01-07 11:22:01.046133|2025-01-07 11:22:01.046133|

### 3️⃣ 과목
|id|department_id|professor_id|name|credits|subject_type|max_capacity|week_day|open_at|close_at|created_at|updated_at|
|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
|1|1|3|자바 프로그래밍|3|MAJOR|30|MON|10:00:00|13:00:00|2025-02-10 10:22:01.046133|2025-02-10 10:22:01.046133|
|2|1|3|컴퓨터의 이해|2|ELECTIVE|20|WED|10:00:00|12:00:00|2025-02-10 10:22:01.046133|2025-02-10 10:22:01.046133|

### 4️⃣ 수강 신청
|id|student_id|subject_id|created_at|
|:---:|:---:|:---:|:---:|
|1|2|1|2025-02-24 10:22:01.046133|
|2|2|2|2025-02-24 10:23:01.046133|
