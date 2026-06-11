package com.vibhorpatil.schoolmanagement.domain.model

data class Student(

    val studentId: Long,

    val studentCode: String,

    val fullName: String,

    val email: String,

    val mobileNumber: String,

    val enrollmentDate: Long,

    val profilePhoto: String?,

    val address: String?,

    val isActive: Boolean,

    val createdAt: Long,

    val updatedAt: Long
)
