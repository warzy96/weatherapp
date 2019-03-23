package com.project.personal.weatherapp.ui.summary

import com.project.personal.domain.model.WeatherModel

class CitiesSummaryViewModelMapper {

    fun mapCitySummaryViewModel(weatherModel: WeatherModel): CitySummaryViewModel {
        val currentWeather = weatherModel.weatherDetailsList.first()
        return CitySummaryViewModel(weatherModel.title, 0, currentWeather.currentTemp,
                currentWeather.weatherStateAbbr)

    }
}
