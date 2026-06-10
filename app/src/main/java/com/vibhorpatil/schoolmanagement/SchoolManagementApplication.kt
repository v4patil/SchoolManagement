package com.vibhorpatil.schoolmanagement

import android.app.Application
import com.vibhorpatil.schoolmanagement.di.component.ApplicationComponent
import com.vibhorpatil.schoolmanagement.di.component.DaggerApplicationComponent
import com.vibhorpatil.schoolmanagement.di.module.ApplicationModule

class SchoolManagementApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        getDependencies()

    }

    private fun getDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }
}