package com.vibhorpatil.schoolmanagement.di.component

import com.vibhorpatil.schoolmanagement.SchoolManagementApplication
import com.vibhorpatil.schoolmanagement.data.local.repositories.StudentRepositoryImpl
import com.vibhorpatil.schoolmanagement.di.module.ApplicationModule
import com.vibhorpatil.schoolmanagement.di.module.DatabaseModule
import com.vibhorpatil.schoolmanagement.di.module.RepositoryModule
import com.vibhorpatil.schoolmanagement.utils.DispatcherProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, DatabaseModule::class, RepositoryModule::class])
interface ApplicationComponent {

    fun inject(application: SchoolManagementApplication)

    fun studentRepository(): StudentRepositoryImpl

    fun dispatcherProvider(): DispatcherProvider
}