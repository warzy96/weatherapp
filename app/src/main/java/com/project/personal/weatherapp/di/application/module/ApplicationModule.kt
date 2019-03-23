package com.project.personal.weatherapp.di.application.module

import android.content.Context
import com.project.personal.weatherapp.app.WeatherApplication
import com.project.personal.weatherapp.di.application.ForApplication
import com.project.personal.weatherapp.ui.fivedayforecast.FiveDayForecastViewModelMapper
import com.project.personal.weatherapp.ui.search.SearchViewModelMapper
import com.project.personal.weatherapp.ui.summary.CitiesSummaryViewModelMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val weatherApplication: WeatherApplication) {

    @Provides
    @Singleton
    @ForApplication
    fun provideContext(): Context {
        return weatherApplication
    }

    @Provides
    @Singleton
    fun provideWeatherApplication(): WeatherApplication {
        return weatherApplication
    }

    @Provides
    @Singleton
    fun provideFiveDayForecastViewModelMapper(): FiveDayForecastViewModelMapper {
        return FiveDayForecastViewModelMapper()
    }

    @Provides
    @Singleton
    fun provideSearchViewModelMapper(): SearchViewModelMapper {
        return SearchViewModelMapper()
    }

    @Provides
    @Singleton
    fun provideSummaryViewModelMapper(): CitiesSummaryViewModelMapper {
        return CitiesSummaryViewModelMapper()
    }
}