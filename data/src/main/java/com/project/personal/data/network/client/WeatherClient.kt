package com.project.personal.data.network.client

import com.project.personal.data.network.mappers.WeatherMapper
import com.project.personal.data.network.service.WeatherService
import com.project.personal.domain.model.CitySearchResult
import com.project.personal.domain.model.WeatherModel
import kotlinx.coroutines.*

class WeatherClient(private val weatherService: WeatherService,
                    private val weatherMapper: WeatherMapper) {

    fun getCitySearchResults(cityName: String): Deferred<List<CitySearchResult>> {

        return GlobalScope.async(Dispatchers.Default, CoroutineStart.DEFAULT, null, {
            weatherMapper.mapCitySearchModel(weatherService.citySearch(cityName).await())
        })
    }

    fun getCityDetails(cityId: Int): Deferred<WeatherModel> {
        return GlobalScope.async(Dispatchers.Default, CoroutineStart.DEFAULT, null, {
            weatherMapper.mapWeatherModel(weatherService.cityDetailsEntity(cityId).await())
        })
    }
}