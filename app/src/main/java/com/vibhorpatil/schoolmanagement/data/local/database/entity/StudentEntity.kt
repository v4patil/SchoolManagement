package com.vibhorpatil.schoolmanagement.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vibhorpatil.schoolmanagement.utils.Constants


@Entity(tableName = Constants.StudentConstants.TABLE_NAME)
data class StudentEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constants.StudentConstants.COLUMN_STUDENT_ID)
    val studentId: Long = 0,

    @ColumnInfo(name = Constants.StudentConstants.COLUMN_STUDENT_CODE)
    val studentCode: String,

    @ColumnInfo(name = Constants.StudentConstants.COLUMN_FULL_NAME)
    val fullName: String,

    @ColumnInfo(name = Constants.StudentConstants.COLUMN_EMAIL)
    val email: String,

    @ColumnInfo(name = Constants.StudentConstants.COLUMN_MOBILE_NUMBER)
    val mobileNumber: String,

    @ColumnInfo(name = Constants.StudentConstants.COLUMN_ENROLLMENT_DATE)
    val enrollmentDate: Long,

    @ColumnInfo(name = Constants.StudentConstants.COLUMN_PROFILE_PHOTO)
    val profilePhoto: String? = null,

    @ColumnInfo(name = Constants.StudentConstants.COLUMN_ADDRESS)
    val address: String? = null,

    @ColumnInfo(name = Constants.StudentConstants.COLUMN_IS_ACTIVE)
    val isActive: Boolean = true,

    @ColumnInfo(name = Constants.StudentConstants.COLUMN_CREATED_AT)
    val createdAt: Long = System.currentTimeMillis(),

    @ColumnInfo(name = Constants.StudentConstants.COLUMN_UPDATED_AT)
    val updatedAt: Long = System.currentTimeMillis()
)
