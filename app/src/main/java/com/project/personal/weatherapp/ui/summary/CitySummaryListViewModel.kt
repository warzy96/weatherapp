package com.project.personal.weatherapp.ui.summary

class CitySummaryListViewModel(val cities: ArrayList<CitySummaryViewModel>) {

    fun add(citySummaryViewModel: CitySummaryViewModel) {
        cities.add(citySummaryViewModel)
    }
}