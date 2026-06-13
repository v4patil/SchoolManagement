package com.vibhorpatil.schoolmanagement.utils

import com.vibhorpatil.schoolmanagement.domain.model.Course
import com.vibhorpatil.schoolmanagement.domain.model.Student

object PreviewData {

    val dummyStudent = Student(
        1L,
        "STU001",
        "Vibhor Patil",
        "vibhor@example.com",
        "7350916174",
        System.currentTimeMillis(),
        null,
        "Nagpur, India",
        true,
        System.currentTimeMillis(),
        System.currentTimeMillis()
    )


    val dummyCourse = Course(
        courseId = 1L,
        courseCode = "CS101",
        courseName = "Computer Science Fundamentals",
        description = "An introductory course covering programming basics, algorithms, and data structures.",
        durationInMonths = 6,
        fees = 15000.0,
        instructorName = "John Doe",
        isActive = true,
        createdAt = System.currentTimeMillis(),
        updatedAt = System.currentTimeMillis()
    )
}