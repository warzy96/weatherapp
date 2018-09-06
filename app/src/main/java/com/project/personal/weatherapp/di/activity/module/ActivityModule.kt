package com.project.personal.weatherapp.di.activity.module

import android.content.Context
import com.project.personal.weatherapp.di.activity.ActivityScope
import com.project.personal.weatherapp.di.activity.DaggerActivity
import com.project.personal.weatherapp.di.activity.ForActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val daggerActivity: DaggerActivity) {

    @Provides
    @ActivityScope
    @ForActivity
    fun provideActivityContext(): Context {
        return daggerActivity
    }
}