package com.project.personal.domain.repository

import com.project.personal.domain.Result
import com.project.personal.domain.model.City
import com.project.personal.domain.model.CitySearchResults
import com.project.personal.domain.model.WeatherModel
import kotlinx.coroutines.Deferred

interface WeatherRepository {

    suspend fun citySearch(cityName: String): Deferred<Result<CitySearchResults>>

    suspend fun fiveDayForecast(cityId: Int): Deferred<Result<WeatherModel>>

    suspend fun citySearch(latitude: Double, longitude: Double): Deferred<Result<CitySearchResults>>

    fun insertCity(city: City)

    fun fetchCities(): List<City>
}