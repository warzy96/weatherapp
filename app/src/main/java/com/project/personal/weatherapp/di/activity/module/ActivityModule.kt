package com.project.personal.weatherapp.di.activity.module

import android.content.Context
import androidx.fragment.app.FragmentManager
import android.view.LayoutInflater
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
    fun provideRouter(fragmentManager: androidx.fragment.app.FragmentManager): Router {
        return Router(daggerActivity, fragmentManager)
    }

    @Provides
    @ActivityScope
    fun provideFragmentManager(): androidx.fragment.app.FragmentManager {
        return daggerActivity.supportFragmentManager
    }

    @Provides
    @ActivityScope
    fun provideLayoutInflater(): LayoutInflater {
        return daggerActivity.layoutInflater
    }
}