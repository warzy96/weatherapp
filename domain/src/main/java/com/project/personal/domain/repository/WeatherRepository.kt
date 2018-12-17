package com.project.personal.domain.repository

import com.project.personal.domain.model.CitySearchResult
import com.project.personal.domain.model.WeatherModel
import kotlinx.coroutines.Deferred

interface WeatherRepository {

    suspend fun citySearch(cityName: String): Deferred<List<CitySearchResult>>

    suspend fun fiveDayForecast(cityId: Int): Deferred<WeatherModel>
}