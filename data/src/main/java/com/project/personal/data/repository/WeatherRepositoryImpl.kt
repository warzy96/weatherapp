package com.project.personal.data.repository

import com.project.personal.data.network.client.WeatherClient
import com.project.personal.domain.model.CitySearchResult
import com.project.personal.domain.model.WeatherModel
import com.project.personal.domain.repository.WeatherRepository
import kotlinx.coroutines.Deferred

class WeatherRepositoryImpl(private val weatherClient: WeatherClient) : WeatherRepository {

    override suspend fun fiveDayForecast(cityId: Int): Deferred<WeatherModel> {
        return weatherClient.getCityDetails(cityId)
    }

    override suspend fun citySearch(cityName: String): Deferred<List<CitySearchResult>> {
        return weatherClient.getCitySearchResults(cityName)
    }


}