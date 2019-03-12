package com.project.personal.data.repository

import com.project.personal.data.database.crudder.CityCrudder
import com.project.personal.data.network.client.WeatherClient
import com.project.personal.domain.Result
import com.project.personal.domain.model.City
import com.project.personal.domain.model.CitySearchResults
import com.project.personal.domain.model.WeatherModel
import com.project.personal.domain.repository.WeatherRepository
import kotlinx.coroutines.Deferred

class WeatherRepositoryImpl(private val weatherClient: WeatherClient,
                            private val cityCrudder: CityCrudder) : WeatherRepository {
    override fun fetchCities(): List<City> {
        return cityCrudder.fetchAll()
    }

    override fun insertCity(city: City) {
        cityCrudder.insertCity(city)
    }

    override suspend fun citySearch(latitude: Double, longitude: Double): Deferred<Result<CitySearchResults>> {
        return weatherClient.getCitySearchResults(latitude, longitude)
    }

    override suspend fun fiveDayForecast(cityId: Int): Deferred<Result<WeatherModel>> {
        return weatherClient.getCityDetails(cityId)
    }

    override suspend fun citySearch(cityName: String): Deferred<Result<CitySearchResults>> {
        return weatherClient.getCitySearchResults(cityName)
    }

}