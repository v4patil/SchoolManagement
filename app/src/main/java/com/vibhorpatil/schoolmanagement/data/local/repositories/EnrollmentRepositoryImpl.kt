package com.vibhorpatil.schoolmanagement.data.local.repositories

import com.vibhorpatil.schoolmanagement.data.local.database.dao.EnrollmentDao
import com.vibhorpatil.schoolmanagement.data.local.database.entity.EnrollmentEntity
import com.vibhorpatil.schoolmanagement.domain.repositories.EnrollmentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EnrollmentRepositoryImpl @Inject constructor(private val enrollmentDao: EnrollmentDao) :
    EnrollmentRepository {

    override suspend fun insertEnrollment(enrollment: EnrollmentEntity): Long {
        return enrollmentDao.insertEnrollment(enrollment)
    }

    override suspend fun updateEnrollment(enrollment: EnrollmentEntity) {
        return enrollmentDao.updateEnrollment(enrollment)
    }

    override suspend fun deleteEnrollment(enrollment: EnrollmentEntity) {
        return enrollmentDao.deleteEnrollment(enrollment)
    }

    override suspend fun deleteEnrollmentById(enrollmentId: Long) {
        return enrollmentDao.deleteEnrollmentById(enrollmentId)
    }

    override fun getAllEnrollments(): Flow<List<EnrollmentEntity>> {
        return enrollmentDao.getAllEnrollments()
    }

    override suspend fun getEnrollmentById(enrollmentId: Long): EnrollmentEntity? {
        return enrollmentDao.getEnrollmentById(enrollmentId)
    }

    override suspend fun getCoursesForStudent(studentId: Long): List<EnrollmentEntity> {
        return enrollmentDao.getCoursesForStudent(studentId)
    }

    override suspend fun deleteEnrollment(studentId: Long, courseId: Long) {
        return enrollmentDao.deleteEnrollment(studentId, courseId)
    }
}