package com.project.personal.data.database.crudder

import com.project.personal.data.database.dao.CitiesDao
import com.project.personal.data.database.mappers.mapCities
import com.project.personal.data.database.mappers.mapDbCity
import com.project.personal.domain.model.City

class CityCrudder(private val citiesDao: CitiesDao) {

    fun insertCity(city: City) {
        citiesDao.insertCityAsync(mapDbCity(city))
    }

    fun fetchAll(): List<City> {
        return mapCities(citiesDao.fetchAllCitiesAsync())
    }
}