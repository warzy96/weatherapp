package com.project.personal.domain.repository

import com.project.personal.domain.model.WeatherModel

interface WeatherRepository {

    fun fetchFiveDayWeather(cityId: Int): WeatherModel
}