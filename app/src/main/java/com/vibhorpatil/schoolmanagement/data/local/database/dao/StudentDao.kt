package com.vibhorpatil.schoolmanagement.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vibhorpatil.schoolmanagement.data.local.database.entity.StudentEntity
import com.vibhorpatil.schoolmanagement.utils.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: StudentEntity): Long

    @Update
    suspend fun updateStudent(student: StudentEntity)

    @Delete
    suspend fun deleteStudent(student: StudentEntity)

    @Query(
        """
        SELECT *
        FROM ${Constants.StudentConstants.TABLE_NAME}
        ORDER BY ${Constants.StudentConstants.COLUMN_FULL_NAME}
        """
    )
    fun getAllStudents(): Flow<List<StudentEntity>>

    @Query(
        """
        SELECT *
        FROM ${Constants.StudentConstants.TABLE_NAME}
        WHERE ${Constants.StudentConstants.COLUMN_STUDENT_ID} = :studentId
        """
    )
    fun getStudentById(studentId: Long): Flow<StudentEntity?>

    @Query(
        """
        SELECT *
        FROM ${Constants.StudentConstants.TABLE_NAME}
        WHERE ${Constants.StudentConstants.COLUMN_IS_ACTIVE} = 1
        ORDER BY ${Constants.StudentConstants.COLUMN_FULL_NAME}
        """
    )
    fun getActiveStudents(): Flow<List<StudentEntity>>

    @Query(
        """
        DELETE FROM ${Constants.StudentConstants.TABLE_NAME}
        WHERE ${Constants.StudentConstants.COLUMN_STUDENT_ID} = :studentId
        """
    )
    suspend fun deleteStudentById(studentId: Long)

    @Query(
        """
        SELECT COUNT(*)
        FROM ${Constants.StudentConstants.TABLE_NAME}
        """
    )
    suspend fun getStudentCount(): Int

    @Query(
        """
    SELECT COUNT(*)
    FROM ${Constants.StudentConstants.TABLE_NAME}
    """
    )
    fun observeStudentCount(): Flow<Int>
}