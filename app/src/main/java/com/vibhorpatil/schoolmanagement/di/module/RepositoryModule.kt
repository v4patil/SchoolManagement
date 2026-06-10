package com.vibhorpatil.schoolmanagement.di.module

import com.vibhorpatil.schoolmanagement.data.local.database.dao.CourseDao
import com.vibhorpatil.schoolmanagement.data.local.database.dao.EnrollmentDao
import com.vibhorpatil.schoolmanagement.data.local.database.dao.StudentDao
import com.vibhorpatil.schoolmanagement.data.local.repositories.CourseRepositoryImpl
import com.vibhorpatil.schoolmanagement.data.local.repositories.EnrollmentRepositoryImpl
import com.vibhorpatil.schoolmanagement.data.local.repositories.StudentRepositoryImpl
import com.vibhorpatil.schoolmanagement.domain.repositories.CourseRepository
import com.vibhorpatil.schoolmanagement.domain.repositories.EnrollmentRepository
import com.vibhorpatil.schoolmanagement.domain.repositories.StudentRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideStudentRepository(studentDao: StudentDao): StudentRepository {
        return StudentRepositoryImpl(studentDao)
    }

    @Provides
    @Singleton
    fun provideCourseRepository(courseDao: CourseDao): CourseRepository {
        return CourseRepositoryImpl(courseDao)
    }

    @Provides
    @Singleton
    fun provideEnrollmentRepository(enrollmentDao: EnrollmentDao): EnrollmentRepository {
        return EnrollmentRepositoryImpl(enrollmentDao)
    }
}