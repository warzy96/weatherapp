package com.project.personal.weatherapp.di.fragment.module

import com.project.personal.weatherapp.di.fragment.DaggerFragment
import com.project.personal.weatherapp.di.fragment.FragmentScope
import com.project.personal.weatherapp.ui.fivedayforecast.FiveDayForecastContract
import com.project.personal.weatherapp.ui.fivedayforecast.FiveDayForecastFragment
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(private val daggerFragment: DaggerFragment) {


    @Provides
    @FragmentScope
    fun provideFiveDayForecastFragment() : FiveDayForecastContract.View {
        return FiveDayForecastFragment.newInstance()
    }
}