package com.project.personal.weatherapp.di.application

import com.project.personal.weatherapp.app.WeatherApplication

interface ApplicationComponentInjects {

    fun inject(weatherApplication: WeatherApplication)
}