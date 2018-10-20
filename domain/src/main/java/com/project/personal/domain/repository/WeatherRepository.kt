package com.project.personal.domain.repository

import com.project.personal.domain.model.CitySearchResult

interface WeatherRepository {

    suspend fun citySearch(cityName: String): List<CitySearchResult>
}