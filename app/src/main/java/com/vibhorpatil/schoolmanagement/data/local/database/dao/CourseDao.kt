package com.vibhorpatil.schoolmanagement.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vibhorpatil.schoolmanagement.data.local.database.entity.CourseEntity
import com.vibhorpatil.schoolmanagement.utils.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourse(courseEntity: CourseEntity): Long

    @Update
    suspend fun updateCourse(courseEntity: CourseEntity)

    @Delete
    suspend fun deleteCourse(courseEntity: CourseEntity)

    @Query(
        """
        SELECT *
        FROM ${Constants.CourseConstants.TABLE_NAME}
        ORDER BY ${Constants.CourseConstants.COLUMN_COURSE_NAME}
        """
    )
    fun getAllCourses(): Flow<List<CourseEntity>>

    @Query(
        """
        SELECT *
        FROM ${Constants.CourseConstants.TABLE_NAME}
        WHERE ${Constants.CourseConstants.COLUMN_COURSE_ID} = :courseId
        """
    )
    suspend fun getCourseById(courseId: Long): CourseEntity?

    @Query(
        """
        DELETE FROM ${Constants.CourseConstants.TABLE_NAME}
        WHERE ${Constants.CourseConstants.COLUMN_COURSE_ID} = :courseId
        """
    )
    suspend fun deleteCourseById(courseId: Long)

    @Query(
        """
    SELECT COUNT(*)
    FROM ${Constants.CourseConstants.TABLE_NAME}
    """
    )
    fun observeCourseCount(): Flow<Int>


}