package com.vibhorpatil.schoolmanagement.data.local.mapper

import com.vibhorpatil.schoolmanagement.data.local.database.entity.CourseEntity
import com.vibhorpatil.schoolmanagement.domain.model.Course

fun CourseEntity.toDomain(): Course {
    return Course(
        courseId = courseId,
        courseCode = courseCode,
        courseName = courseName,
        profilePhoto = courseProfilePhoto,
        description = description,
        durationInMonths = durationInMonths,
        fees = fees,
        instructorName = instructorName,
        isActive = isActive,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun Course.toEntity(): CourseEntity {
    return CourseEntity(
        courseId = courseId,
        courseCode = courseCode,
        courseName = courseName,
        courseProfilePhoto = profilePhoto,
        description = description,
        durationInMonths = durationInMonths,
        fees = fees,
        instructorName = instructorName,
        isActive = isActive,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}