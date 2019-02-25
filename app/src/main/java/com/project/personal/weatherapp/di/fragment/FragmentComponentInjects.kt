package com.project.personal.weatherapp.di.fragment

import com.project.personal.weatherapp.ui.fivedayforecast.FiveDayForecastFragment
import com.project.personal.weatherapp.ui.fivedayforecast.FiveDayForecastPresenter
import com.project.personal.weatherapp.ui.pager.ActivityFragment
import com.project.personal.weatherapp.ui.search.SearchFragment

interface FragmentComponentInjects {

    fun inject(fiveDayForecastFragment: FiveDayForecastFragment)

    fun inject(fiveDayForecastPresenter: FiveDayForecastPresenter)

    fun inject(activityFragment: ActivityFragment)

    fun inject(searchFragment: SearchFragment)
}