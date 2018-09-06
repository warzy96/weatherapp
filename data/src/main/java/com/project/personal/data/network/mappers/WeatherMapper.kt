package com.project.personal.data.network.mappers

import com.project.personal.data.network.model.ApiCityDetails
import com.project.personal.data.network.model.ConsolidatedWeather
import com.project.personal.domain.model.WeatherDetailsModel
import com.project.personal.domain.model.WeatherModel

class WeatherMapper {

    fun mapWeatherModel(apiCityDetails: ApiCityDetails): WeatherModel {
        return WeatherModel(apiCityDetails.title, apiCityDetails.sunRise, apiCityDetails.sunSet, apiCityDetails.timezoneName,
                mapWeatherDetailsModel(apiCityDetails.consolidatedWeather))
    }

    fun mapWeatherDetailsModel(consolidatedWeatherList: List<ConsolidatedWeather>): List<WeatherDetailsModel> {
        val weatherDetailsModelList: ArrayList<WeatherDetailsModel> = ArrayList()
        consolidatedWeatherList.forEach {
            weatherDetailsModelList.add(WeatherDetailsModel(it.id, it.applicableDate, it.weatherState, it.weatherStateAbbr, it.windSpeed,
                    it.windDirection, it.minTemp, it.maxTemp, it.currentTemp, it.airPressure, it.humidity, it.visibility, it.predictability))
        }
        return weatherDetailsModelList
    }
}