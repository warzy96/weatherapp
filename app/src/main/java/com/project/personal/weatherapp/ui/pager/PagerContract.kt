package com.project.personal.weatherapp.ui.pager

import com.project.personal.domain.model.City

interface PagerContract {

    interface Presenter {
        fun start()
    }

    interface Adapter {
        fun update(cities: List<City>)
    }
}