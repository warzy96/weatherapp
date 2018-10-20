package com.project.personal.data.repository

import com.project.personal.data.network.client.WeatherClient
import com.project.personal.domain.model.CitySearchResult
import com.project.personal.domain.repository.WeatherRepository

class WeatherRepositoryImpl(private val weatherClient: WeatherClient) : WeatherRepository {

    override suspend fun citySearch(cityName: String): List<CitySearchResult> {
        return weatherClient.getCitySearchResults(cityName)
    }


}