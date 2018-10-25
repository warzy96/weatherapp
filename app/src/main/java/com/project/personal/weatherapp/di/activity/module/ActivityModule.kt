package com.project.personal.weatherapp.di.activity.module

import android.content.Context
import android.support.v4.app.FragmentManager
import com.project.personal.weatherapp.di.activity.ActivityScope
import com.project.personal.weatherapp.di.activity.DaggerActivity
import com.project.personal.weatherapp.di.activity.ForActivity
import com.project.personal.weatherapp.router.Router
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

    @Provides
    @ActivityScope
    fun provideRouter(fragmentManager: FragmentManager): Router {
        return Router(daggerActivity, fragmentManager)
    }

    @Provides
    @ActivityScope
    fun provideFragmentManager(): FragmentManager {
        return daggerActivity.supportFragmentManager
    }
}