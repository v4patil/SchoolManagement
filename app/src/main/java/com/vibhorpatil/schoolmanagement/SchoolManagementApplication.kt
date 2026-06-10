package com.vibhorpatil.schoolmanagement

import android.app.Application
import com.vibhorpatil.schoolmanagement.data.local.database.dao.StudentDao
import com.vibhorpatil.schoolmanagement.data.local.database.entity.StudentEntity
import com.vibhorpatil.schoolmanagement.di.component.ApplicationComponent
import com.vibhorpatil.schoolmanagement.di.component.DaggerApplicationComponent
import com.vibhorpatil.schoolmanagement.di.module.ApplicationModule
import com.vibhorpatil.schoolmanagement.di.module.DatabaseModule
import com.vibhorpatil.schoolmanagement.di.module.RepositoryModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SchoolManagementApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    @Inject
    lateinit var studentDao: StudentDao

    override fun onCreate() {
        super.onCreate()
        getDependencies()

    }

    private fun getDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .databaseModule(DatabaseModule(this))
            .repositoryModule(RepositoryModule())
            .build()
        applicationComponent.inject(this)
    }
}