package com.vibhorpatil.schoolmanagement.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vibhorpatil.schoolmanagement.data.local.database.entity.EnrollmentEntity
import com.vibhorpatil.schoolmanagement.utils.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface EnrollmentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEnrollment(enrollment: EnrollmentEntity): Long

    @Update
    suspend fun updateEnrollment(enrollment: EnrollmentEntity)

    @Delete
    suspend fun deleteEnrollment(enrollment: EnrollmentEntity)

    @Query(
        """
        SELECT *
        FROM ${Constants.EnrollmentConstants.TABLE_NAME}
        """
    )
    fun getAllEnrollments(): Flow<List<EnrollmentEntity>>

    @Query(
        """
        SELECT *
        FROM ${Constants.EnrollmentConstants.TABLE_NAME}
        WHERE ${Constants.EnrollmentConstants.COLUMN_ENROLLMENT_ID} = :enrollmentId
        """
    )
    suspend fun getEnrollmentById(enrollmentId: Long): EnrollmentEntity?

    @Query(
        """
        DELETE FROM ${Constants.EnrollmentConstants.TABLE_NAME}
        WHERE ${Constants.EnrollmentConstants.COLUMN_ENROLLMENT_ID} = :enrollmentId
        """
    )
    suspend fun deleteEnrollmentById(enrollmentId: Long)

    @Query("""
    SELECT *
    FROM ${Constants.EnrollmentConstants.TABLE_NAME}
    WHERE ${Constants.EnrollmentConstants.COLUMN_STUDENT_ID} = :studentId
    """)
    suspend fun getCoursesForStudent(studentId: Long): List<EnrollmentEntity>

    @Query("""
    DELETE FROM ${Constants.EnrollmentConstants.TABLE_NAME}
    WHERE ${Constants.EnrollmentConstants.COLUMN_STUDENT_ID} = :studentId
    AND ${Constants.EnrollmentConstants.COLUMN_COURSE_ID} = :courseId
    """)
    suspend fun deleteEnrollment(studentId: Long, courseId: Long)
}