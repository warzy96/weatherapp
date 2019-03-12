package com.project.personal.data.database.mappers

import com.project.personal.data.database.model.DbCity
import com.project.personal.domain.model.City

fun mapDbCity(city: City): DbCity {
    return DbCity(city.woeid, city.title)
}

fun mapCities(dbCities: List<DbCity>): ArrayList<City> {
    val list = ArrayList<City>()
    dbCities.forEach {
        list.add(City(it.cityName, it.woeid))
    }
    return list
}