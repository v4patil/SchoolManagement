package com.vibhorpatil.schoolmanagement.data.local.mapper

import com.vibhorpatil.schoolmanagement.data.local.database.entity.StudentEntity
import com.vibhorpatil.schoolmanagement.domain.model.Student


fun StudentEntity.toDomain() : Student {
    return Student(
        studentId = studentId,
        studentCode = studentCode,
        fullName = fullName,
        email = email,
        mobileNumber = mobileNumber,
        enrollmentDate = enrollmentDate,
        profilePhoto = profilePhoto,
        address = address,
        isActive = isActive,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun Student.toEntity() : StudentEntity {
    return StudentEntity(
        studentId = studentId,
        studentCode = studentCode,
        fullName = fullName,
        email = email,
        mobileNumber = mobileNumber,
        enrollmentDate = enrollmentDate,
        profilePhoto = profilePhoto,
        address = address,
        isActive = isActive,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}