package com.project.personal.weatherapp.ui.fivedayforecast

import com.project.personal.domain.model.WeatherModel

interface FiveDayForecastContract {

    interface Presenter {

        fun start(cityId: Int)

        fun setView(fiveDayForecastContractView: FiveDayForecastContract.View)
    }

    interface View {
        fun render(weatherModel: WeatherModel)
    }
}