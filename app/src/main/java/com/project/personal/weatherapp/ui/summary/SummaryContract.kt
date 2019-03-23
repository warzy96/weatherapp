package com.project.personal.weatherapp.ui.summary

interface SummaryContract {

    interface View {
        fun render(citySummaryListViewModel: CitySummaryListViewModel)
    }

    interface Presenter {
        fun start()

        fun setView(view: SummaryContract.View)
    }
}