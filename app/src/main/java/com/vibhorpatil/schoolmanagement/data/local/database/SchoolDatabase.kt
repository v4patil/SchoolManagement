package com.vibhorpatil.schoolmanagement.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vibhorpatil.schoolmanagement.data.local.database.dao.CourseDao
import com.vibhorpatil.schoolmanagement.data.local.database.dao.EnrollmentDao
import com.vibhorpatil.schoolmanagement.data.local.database.dao.StudentDao
import com.vibhorpatil.schoolmanagement.data.local.database.entity.CourseEntity
import com.vibhorpatil.schoolmanagement.data.local.database.entity.EnrollmentEntity
import com.vibhorpatil.schoolmanagement.data.local.database.entity.StudentEntity

@Database(
    entities = [StudentEntity::class, CourseEntity::class, EnrollmentEntity::class],
    version = 1,
    exportSchema = false
)
abstract class SchoolDatabase : RoomDatabase() {

    abstract fun studentDao(): StudentDao
    abstract fun courseDao(): CourseDao
    abstract fun enrollmentDao(): EnrollmentDao

}