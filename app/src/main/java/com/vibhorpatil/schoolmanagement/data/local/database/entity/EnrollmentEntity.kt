package com.vibhorpatil.schoolmanagement.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.vibhorpatil.schoolmanagement.utils.Constants

@Entity(
    tableName = Constants.EnrollmentConstants.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = StudentEntity::class,
            parentColumns = [Constants.StudentConstants.COLUMN_STUDENT_ID],
            childColumns = [Constants.EnrollmentConstants.COLUMN_STUDENT_ID],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = CourseEntity::class,
            parentColumns = [Constants.CourseConstants.COLUMN_COURSE_ID],
            childColumns = [Constants.EnrollmentConstants.COLUMN_COURSE_ID],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = [Constants.EnrollmentConstants.COLUMN_STUDENT_ID]),
        Index(value = [Constants.EnrollmentConstants.COLUMN_COURSE_ID])
    ]
)
data class EnrollmentEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constants.EnrollmentConstants.COLUMN_ENROLLMENT_ID)
    val enrollmentId: Long = 0,

    @ColumnInfo(name = Constants.EnrollmentConstants.COLUMN_STUDENT_ID)
    val studentId: Long,

    @ColumnInfo(name = Constants.EnrollmentConstants.COLUMN_COURSE_ID)
    val courseId: Long,

    @ColumnInfo(name = Constants.EnrollmentConstants.COLUMN_ENROLLMENT_DATE)
    val enrollmentDate: Long,

    @ColumnInfo(name = Constants.EnrollmentConstants.COLUMN_COURSE_FEE)
    val courseFee: Double,

    @ColumnInfo(name = Constants.EnrollmentConstants.COLUMN_STATUS)
    val status: Int = Constants.EnrollmentConstants.STATUS_ACTIVE
)
