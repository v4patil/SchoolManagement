package com.vibhorpatil.schoolmanagement.domain.repositories

import com.vibhorpatil.schoolmanagement.data.local.database.entity.CourseEntity
import kotlinx.coroutines.flow.Flow

interface CourseRepository {

    fun getAllCourses(): Flow<List<CourseEntity>>

    suspend fun getCourseById(courseId: Long): CourseEntity?

    suspend fun insertCourse(course: CourseEntity): Long

    suspend fun updateCourse(course: CourseEntity)

    suspend fun deleteCourse(course: CourseEntity)

    suspend fun deleteCourseById(courseId: Long)

}