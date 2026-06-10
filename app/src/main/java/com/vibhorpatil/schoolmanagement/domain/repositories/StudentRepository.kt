package com.vibhorpatil.schoolmanagement.domain.repositories

import com.vibhorpatil.schoolmanagement.data.local.database.entity.StudentEntity
import kotlinx.coroutines.flow.Flow

interface StudentRepository {

    suspend fun addStudent(student: StudentEntity)

    suspend fun updateStudent(student: StudentEntity)

    suspend fun deleteStudent(student: StudentEntity)

    suspend fun getStudents(): Flow<List<StudentEntity>>

    suspend fun getStudent(studentId: Long): Flow<StudentEntity?>
}