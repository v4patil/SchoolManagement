package com.vibhorpatil.schoolmanagement.di.module

import android.app.Application
import androidx.room.Room
import com.vibhorpatil.schoolmanagement.data.local.database.SchoolDatabase
import com.vibhorpatil.schoolmanagement.data.local.database.dao.CourseDao
import com.vibhorpatil.schoolmanagement.data.local.database.dao.EnrollmentDao
import com.vibhorpatil.schoolmanagement.data.local.database.dao.StudentDao
import com.vibhorpatil.schoolmanagement.utils.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(val application: Application) {

    @Provides
    @Singleton
    fun provideDatabase(): SchoolDatabase {
        return Room.databaseBuilder(
            application, SchoolDatabase::class.java,
            Constants.DbConstants.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideStudentDao(database: SchoolDatabase): StudentDao = database.studentDao()

    @Provides
    @Singleton
    fun provideCourseDao(database: SchoolDatabase): CourseDao = database.courseDao()

    @Provides
    @Singleton
    fun provideEnrollmentDao(database: SchoolDatabase): EnrollmentDao = database.enrollmentDao()

}