package com.project.personal.data.repository

import com.project.personal.data.network.client.WeatherClient
import com.project.personal.domain.model.WeatherModel
import com.project.personal.domain.repository.WeatherRepository

class WeatherRepositoryImpl(private val weatherClient: WeatherClient) : WeatherRepository {

    override fun fetchFiveDayWeather(cityId: Int): WeatherModel {
        return weatherClient.getCityDetails(cityId)
    }
}