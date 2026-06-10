package com.vibhorpatil.schoolmanagement.data.local.repositories

import com.vibhorpatil.schoolmanagement.data.local.database.dao.CourseDao
import com.vibhorpatil.schoolmanagement.data.local.database.entity.CourseEntity
import com.vibhorpatil.schoolmanagement.domain.repositories.CourseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CourseRepositoryImpl @Inject constructor(
    private val courseDao: CourseDao
) : CourseRepository {

    override suspend fun insertCourse(course: CourseEntity): Long {
        return courseDao.insertCourse(course)
    }

    override suspend fun updateCourse(course: CourseEntity) {
        courseDao.updateCourse(course)
    }

    override suspend fun deleteCourse(course: CourseEntity) {
        courseDao.deleteCourse(course)
    }

    override suspend fun deleteCourseById(courseId: Long) {
        courseDao.deleteCourseById(courseId)
    }

    override fun getAllCourses(): Flow<List<CourseEntity>> {
        return courseDao.getAllCourses()
    }

    override suspend fun getCourseById(courseId: Long): CourseEntity? {
        return courseDao.getCourseById(courseId)
    }
}