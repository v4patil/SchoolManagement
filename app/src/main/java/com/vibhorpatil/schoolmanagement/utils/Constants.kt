package com.vibhorpatil.schoolmanagement.utils

object Constants {

    object DbConstants {
        const val DATABASE_NAME = "school_db"
    }

    object StudentConstants {
        const val TABLE_NAME = "students"

        const val COLUMN_STUDENT_ID = "studentId"
        const val COLUMN_STUDENT_CODE = "studentCode"
        const val COLUMN_FULL_NAME = "fullName"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_MOBILE_NUMBER = "mobileNumber"
        const val COLUMN_ENROLLMENT_DATE = "enrollmentDate"
        const val COLUMN_PROFILE_PHOTO = "profilePhoto"
        const val COLUMN_ADDRESS = "address"
        const val COLUMN_IS_ACTIVE = "isActive"
        const val COLUMN_CREATED_AT = "createdAt"
        const val COLUMN_UPDATED_AT = "updatedAt"
    }

    object CourseConstants {
        const val TABLE_NAME = "courses"

        const val COLUMN_COURSE_ID = "courseId"
        const val COLUMN_COURSE_CODE = "courseCode"
        const val COLUMN_COURSE_NAME = "courseName"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_DURATION_IN_MONTHS = "durationInMonths"
        const val COLUMN_FEES = "fees"
        const val COLUMN_INSTRUCTOR_NAME = "instructorName"
        const val COLUMN_IS_ACTIVE = "isActive"
        const val COLUMN_CREATED_AT = "createdAt"
        const val COLUMN_UPDATED_AT = "updatedAt"
    }

    object EnrollmentConstants {
        const val TABLE_NAME = "enrollments"

        const val COLUMN_ENROLLMENT_ID = "enrollmentId"
        const val COLUMN_STUDENT_ID = "studentId"
        const val COLUMN_COURSE_ID = "courseId"
        const val COLUMN_ENROLLMENT_DATE = "enrollmentDate"
        const val COLUMN_COURSE_FEE = "courseFee"
        const val COLUMN_STATUS = "status"

        const val STATUS_ACTIVE = 0
        const val STATUS_COMPLETED = 1
        const val STATUS_CANCELLED = 2
    }
}