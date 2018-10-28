package com.project.personal.weatherapp.di.application.module

import android.content.Context
import com.project.personal.weatherapp.app.WeatherApplication
import com.project.personal.weatherapp.di.application.ForApplication
import com.project.personal.weatherapp.ui.fivedayforecast.FiveDayForecastViewModelMapper
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
}