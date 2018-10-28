package com.project.personal.weatherapp.ui.fivedayforecast

import com.project.personal.domain.model.WeatherDetailsModel
import com.project.personal.domain.model.WeatherModel

class FiveDayForecastViewModelMapper {

    fun mapFiveDayForecastViewModel(weatherDetailsModel: WeatherDetailsModel): FiveDayForecastViewModel {
        return FiveDayForecastViewModel(
                weatherDetailsModel.applicableDate,
                weatherDetailsModel.weatherState,
                weatherDetailsModel.weatherStateAbbr,
                weatherDetailsModel.windSpeed,
                weatherDetailsModel.windDirection,
                weatherDetailsModel.minTemp,
                weatherDetailsModel.maxTemp,
                weatherDetailsModel.currentTemp,
                weatherDetailsModel.airPressure,
                weatherDetailsModel.humidity,
                weatherDetailsModel.visibility)
    }

    fun mapFiveDayForecastViewModels(weatherModel: WeatherModel): FiveDayForecastListViewModel {
        val fiveDayForecastListViewModel = FiveDayForecastListViewModel()
        weatherModel.weatherDetailsList.forEach {
            fiveDayForecastListViewModel.forecasts.add(mapFiveDayForecastViewModel(it))
        }
        return fiveDayForecastListViewModel
    }
}