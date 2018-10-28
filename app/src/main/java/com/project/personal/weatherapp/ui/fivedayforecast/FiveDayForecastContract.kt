package com.project.personal.weatherapp.ui.fivedayforecast

interface FiveDayForecastContract {

    interface Presenter {

        fun start(cityId: Int)

        fun setView(fiveDayForecastContractView: FiveDayForecastContract.View)
    }

    interface View {
        fun render(fiveDayForecastListViewModel: FiveDayForecastListViewModel)
    }
}