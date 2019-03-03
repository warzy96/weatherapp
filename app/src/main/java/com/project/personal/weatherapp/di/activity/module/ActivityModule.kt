package com.project.personal.weatherapp.di.activity.module

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.project.personal.weatherapp.di.activity.ActivityScope
import com.project.personal.weatherapp.di.activity.DaggerActivity
import com.project.personal.weatherapp.di.activity.ForActivity
import com.project.personal.weatherapp.di.fragment.FragmentScope
import com.project.personal.weatherapp.router.Router
import com.project.personal.weatherapp.ui.pager.ForecastPagerAdapter
import com.project.personal.weatherapp.util.LocationProvider
import com.project.personal.weatherapp.util.LocationProviderImpl
import com.project.personal.weatherapp.util.StringUtil
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
    @ForActivity
    fun provideActivity(): AppCompatActivity {
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

    @Provides
    @ActivityScope
    fun provideLocationProvider(@ForActivity activity: AppCompatActivity): LocationProvider {
        return LocationProviderImpl(activity)
    }

    @Provides
    @ActivityScope
    fun providePagerAdapter(stringUtil: StringUtil, @FragmentScope fragmentManager: FragmentManager):
            ForecastPagerAdapter {
        return ForecastPagerAdapter(stringUtil, fragmentManager)
    }
}