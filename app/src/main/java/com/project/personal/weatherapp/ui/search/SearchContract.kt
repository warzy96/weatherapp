package com.project.personal.weatherapp.ui.search

import com.project.personal.domain.model.City

interface SearchContract {

    interface View {
        fun render(searchItemsViewModel: SearchItemsViewModel)
    }

    interface Presenter {
        fun start(cityName: String)

        fun setView(view: SearchContract.View)

        fun saveCity(city: City)
    }
}