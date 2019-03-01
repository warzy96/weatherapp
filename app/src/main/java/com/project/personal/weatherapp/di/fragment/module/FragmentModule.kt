package com.project.personal.weatherapp.di.fragment.module

import android.view.LayoutInflater
import com.project.personal.weatherapp.di.fragment.DaggerFragment
import com.project.personal.weatherapp.di.fragment.FragmentScope
import com.project.personal.weatherapp.ui.fivedayforecast.FiveDayForecastAdapter
import com.project.personal.weatherapp.ui.fivedayforecast.FiveDayForecastContract
import com.project.personal.weatherapp.ui.fivedayforecast.FiveDayForecastLocationFragment
import com.project.personal.weatherapp.ui.pager.ForecastPagerAdapter
import com.project.personal.weatherapp.ui.search.SearchAdapter
import com.project.personal.weatherapp.util.ImageLoader
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(private val daggerFragment: DaggerFragment) {


    @Provides
    @FragmentScope
    fun provideFiveDayForecastFragment(): FiveDayForecastContract.View {
        return FiveDayForecastLocationFragment.newInstance()
    }

    @Provides
    @FragmentScope
    fun provideFiveDayForecastRecyclerAdapter(layoutInflater: LayoutInflater,
                                              imageLoader: ImageLoader): FiveDayForecastAdapter {
        return FiveDayForecastAdapter(layoutInflater, imageLoader)
    }

    @Provides
    @FragmentScope
    fun provideSearchRecyclerViewAdapter(layoutInflater: LayoutInflater, forecastPagerAdapter: ForecastPagerAdapter)
            : SearchAdapter {
        return SearchAdapter(layoutInflater, forecastPagerAdapter)
    }
}