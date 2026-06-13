package com.vibhorpatil.schoolmanagement.di.component

import com.vibhorpatil.schoolmanagement.MainActivity
import com.vibhorpatil.schoolmanagement.di.ActivityScope
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class])
interface ActivityComponent {

    fun inject(activity: MainActivity)
}