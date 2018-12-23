package com.project.personal.weatherapp.ui.fivedayforecast

import com.project.personal.data.Location

interface FiveDayForecastContract {

    interface Presenter {

        fun start(location: Location)

        fun setView(fiveDayForecastContractView: FiveDayForecastContract.View)
    }

    interface View {
        fun render(fiveDayForecastListViewModel: FiveDayForecastListViewModel)
    }
}