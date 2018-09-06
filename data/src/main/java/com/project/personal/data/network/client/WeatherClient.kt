package com.project.personal.data.network.client

import com.project.personal.data.network.mappers.WeatherMapper
import com.project.personal.data.network.model.ApiCity
import com.project.personal.data.network.service.WeatherService
import com.project.personal.domain.model.WeatherModel

class WeatherClient(private val weatherService: WeatherService,
                    private val weatherMapper: WeatherMapper) {

    fun getCity(cityName: String): List<ApiCity> {
        return weatherService.cityEntity(cityName)
    }

    fun getCityDetails(cityId: Int): WeatherModel {
        return weatherMapper.mapWeatherModel(weatherService.cityDetailsEntity(cityId))
    }
}