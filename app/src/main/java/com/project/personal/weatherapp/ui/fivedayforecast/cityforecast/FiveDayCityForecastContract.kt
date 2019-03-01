package com.project.personal.weatherapp.ui.fivedayforecast.cityforecast

import com.project.personal.weatherapp.ui.fivedayforecast.FiveDayForecastListViewModel

interface FiveDayCityForecastContract {

    interface Presenter {

        fun start(id: Int)

        fun setView(fiveDayCityForecastContractView: FiveDayCityForecastContract.View)
    }

    interface View {
        fun render(fiveDayForecastListViewModel: FiveDayForecastListViewModel)
    }
}