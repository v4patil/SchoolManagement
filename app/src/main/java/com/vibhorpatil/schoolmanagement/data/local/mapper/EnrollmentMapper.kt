package com.vibhorpatil.schoolmanagement.data.local.mapper

import com.vibhorpatil.schoolmanagement.data.local.database.entity.EnrollmentEntity
import com.vibhorpatil.schoolmanagement.domain.model.Enrollment

fun EnrollmentEntity.toDomain(): Enrollment {
    return Enrollment(
        enrollmentId = enrollmentId,
        studentId = studentId,
        courseId = courseId,
        enrollmentDate = enrollmentDate,
        courseFee = courseFee,
        status = status
    )
}

fun Enrollment.toEntity(): EnrollmentEntity {
    return EnrollmentEntity(
        enrollmentId = enrollmentId,
        studentId = studentId,
        courseId = courseId,
        enrollmentDate = enrollmentDate,
        courseFee = courseFee,
        status = status
    )
}