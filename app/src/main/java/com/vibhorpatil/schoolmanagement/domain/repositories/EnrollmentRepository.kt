package com.vibhorpatil.schoolmanagement.domain.repositories

import com.vibhorpatil.schoolmanagement.data.local.database.entity.EnrollmentEntity
import kotlinx.coroutines.flow.Flow

interface EnrollmentRepository {

    fun getAllEnrollments(): Flow<List<EnrollmentEntity>>

    suspend fun getEnrollmentById(enrollmentId: Long): EnrollmentEntity?

    suspend fun insertEnrollment(enrollment: EnrollmentEntity): Long

    suspend fun updateEnrollment(enrollment: EnrollmentEntity)

    suspend fun deleteEnrollment(enrollment: EnrollmentEntity)

    suspend fun deleteEnrollmentById(enrollmentId: Long)
}