package com.vibhorpatil.schoolmanagement.di.module

import android.app.Application
import com.vibhorpatil.schoolmanagement.di.ApplicationContext
import com.vibhorpatil.schoolmanagement.utils.DispatcherProvider
import com.vibhorpatil.schoolmanagement.utils.DispatcherProviderImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ApplicationModule(val application: Application) {

    @Provides
    @ApplicationContext
    fun provideContext(): Application {
        return application
    }

    @Provides
    @Singleton
    fun provideDispatcherProvider(): DispatcherProvider {
        return DispatcherProviderImpl()
    }

}