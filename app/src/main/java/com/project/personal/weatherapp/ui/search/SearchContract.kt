package com.project.personal.weatherapp.ui.search

interface SearchContract {

    interface View {
        fun render(searchItemsViewModel: SearchItemsViewModel)
    }

    interface Presenter {
        fun start(cityName: String)

        fun setView(view: SearchContract.View)
    }
}