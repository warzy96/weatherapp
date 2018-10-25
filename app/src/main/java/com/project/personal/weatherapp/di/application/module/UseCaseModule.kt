package com.project.personal.weatherapp.di.application.module

import com.project.personal.domain.interactor.FetchFiveDayForecastUseCase
import com.project.personal.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun provideFetchFiveDayForecastUseCase(weatherRepository: WeatherRepository): FetchFiveDayForecastUseCase {
        return FetchFiveDayForecastUseCase(weatherRepository)
    }
}