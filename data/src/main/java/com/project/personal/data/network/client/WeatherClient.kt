package com.project.personal.data.network.client

import com.project.personal.data.network.mappers.WeatherMapper
import com.project.personal.data.network.service.WeatherService
import com.project.personal.domain.model.CitySearchResult
import com.project.personal.domain.model.WeatherModel
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async

class WeatherClient(private val weatherService: WeatherService,
                    private val weatherMapper: WeatherMapper) {

    suspend fun getCitySearchResults(cityName: String): Deferred<List<CitySearchResult>> {
        return async {
            weatherMapper.mapCitySearchModel(weatherService.citySearch(cityName).await())
        }
    }

    suspend fun getCityDetails(cityId: Int): Deferred<WeatherModel> {
        return async {
            weatherMapper.mapWeatherModel(weatherService.cityDetailsEntity(cityId).await())
        }
    }
}