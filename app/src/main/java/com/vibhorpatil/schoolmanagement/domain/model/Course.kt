package com.vibhorpatil.schoolmanagement.domain.model

data class Course(

    val courseId: Long,

    val courseCode: String,

    val courseName: String,

    val description: String,

    val profilePhoto: String?,

    val durationInMonths: Int,

    val fees: Double,

    val instructorName: String,

    val isActive: Boolean,

    val createdAt: Long,

    val updatedAt: Long
)
