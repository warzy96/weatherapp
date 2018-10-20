package com.project.personal.data.network.client

import com.project.personal.data.network.mappers.WeatherMapper
import com.project.personal.data.network.model.ApiCityDetails
import com.project.personal.data.network.service.WeatherService
import com.project.personal.domain.model.CitySearchResult
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Response

class WeatherClient(private val weatherService: WeatherService,
                    private val weatherMapper: WeatherMapper) {

    suspend fun getCitySearchResults(cityName: String): List<CitySearchResult> {
        val response = weatherService.cityEntity(cityName).await()
        if (response.isSuccessful) {
            return weatherMapper.mapCitySearchModel(response.body())
        } else {
            throw Exception("Api response is unsuccessful! \n$response")
        }
    }

    fun getCityDetails(cityId: Int): Deferred<Response<ApiCityDetails>> {
        return weatherService.cityDetailsEntity(cityId)
    }
}