package com.vibhorpatil.schoolmanagement.domain.model

data class Enrollment(

    val enrollmentId: Long,

    val studentId: Long,

    val courseId: Long,

    val enrollmentDate: Long,

    val courseFee: Double,

    val status: Int
)