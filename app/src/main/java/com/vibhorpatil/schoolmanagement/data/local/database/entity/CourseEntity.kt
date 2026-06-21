package com.vibhorpatil.schoolmanagement.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vibhorpatil.schoolmanagement.utils.Constants

@Entity(tableName = Constants.CourseConstants.TABLE_NAME)
data class CourseEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constants.CourseConstants.COLUMN_COURSE_ID)
    val courseId: Long = 0,

    @ColumnInfo(name = Constants.CourseConstants.COLUMN_COURSE_CODE)
    val courseCode: String,

    @ColumnInfo(name = Constants.CourseConstants.COLUMN_COURSE_NAME)
    val courseName: String,

    @ColumnInfo(name = Constants.CourseConstants.COLUMN_COURSE_PROFILE_PHOTO)
    val courseProfilePhoto: String? = null,

    @ColumnInfo(name = Constants.CourseConstants.COLUMN_DESCRIPTION)
    val description: String,

    @ColumnInfo(name = Constants.CourseConstants.COLUMN_DURATION_IN_MONTHS)
    val durationInMonths: Int,

    @ColumnInfo(name = Constants.CourseConstants.COLUMN_FEES)
    val fees: Double,

    @ColumnInfo(name = Constants.CourseConstants.COLUMN_INSTRUCTOR_NAME)
    val instructorName: String,

    @ColumnInfo(name = Constants.CourseConstants.COLUMN_IS_ACTIVE)
    val isActive: Boolean = true,

    @ColumnInfo(name = Constants.CourseConstants.COLUMN_CREATED_AT)
    val createdAt: Long = System.currentTimeMillis(),

    @ColumnInfo(name = Constants.CourseConstants.COLUMN_UPDATED_AT)
    val updatedAt: Long = System.currentTimeMillis()
)
