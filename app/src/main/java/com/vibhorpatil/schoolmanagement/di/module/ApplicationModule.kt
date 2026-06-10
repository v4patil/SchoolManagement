package com.vibhorpatil.schoolmanagement.di.module

import android.app.Application
import com.vibhorpatil.schoolmanagement.di.ApplicationContext
import dagger.Module
import dagger.Provides


@Module
class ApplicationModule(val application: Application) {

    @Provides
    @ApplicationContext
    fun provideContext(): Application {
        return application
    }


}