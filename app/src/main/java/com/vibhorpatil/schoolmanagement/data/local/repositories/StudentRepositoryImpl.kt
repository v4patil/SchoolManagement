package com.vibhorpatil.schoolmanagement.data.local.repositories

import com.vibhorpatil.schoolmanagement.data.local.database.dao.StudentDao
import com.vibhorpatil.schoolmanagement.data.local.database.entity.StudentEntity
import com.vibhorpatil.schoolmanagement.domain.repositories.StudentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StudentRepositoryImpl @Inject constructor(
    private val studentDao: StudentDao
) : StudentRepository {

    override suspend fun addStudent(student: StudentEntity) {
        studentDao.insertStudent(student)
    }

    override suspend fun updateStudent(student: StudentEntity) {
        studentDao.updateStudent(student)
    }

    override suspend fun deleteStudent(student: StudentEntity) {
        studentDao.deleteStudent(student)
    }

    override suspend fun deleteStudentById(studentId: Long) {
        studentDao.deleteStudentById(studentId)
    }

    override suspend fun getStudents(): Flow<List<StudentEntity>> {
        return studentDao.getAllStudents()
    }

    override suspend fun getStudent(studentId: Long): Flow<StudentEntity?> {
        return studentDao.getStudentById(studentId)
    }

    override fun observeStudentCount(): Flow<Int> {
        return studentDao.observeStudentCount()
    }
}