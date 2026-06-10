package com.vibhorpatil.schoolmanagement.di.component

import com.vibhorpatil.schoolmanagement.SchoolManagementApplication
import com.vibhorpatil.schoolmanagement.di.module.ApplicationModule
import dagger.Component

@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: SchoolManagementApplication)
}