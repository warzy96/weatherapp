package com.project.personal.weatherapp.di.fragment

import com.project.personal.weatherapp.ui.fivedayforecast.FiveDayForecastLocationFragment
import com.project.personal.weatherapp.ui.fivedayforecast.FiveDayForecastPresenter
import com.project.personal.weatherapp.ui.fivedayforecast.cityforecast.FiveDayCityForecastFragment
import com.project.personal.weatherapp.ui.pager.ActivityFragment
import com.project.personal.weatherapp.ui.search.SearchFragment
import com.project.personal.weatherapp.ui.summary.SummaryFragment

interface FragmentComponentInjects {

    fun inject(fiveDayForecastLocationFragment: FiveDayForecastLocationFragment)

    fun inject(fiveDayForecastPresenter: FiveDayForecastPresenter)

    fun inject(activityFragment: ActivityFragment)

    fun inject(searchFragment: SearchFragment)

    fun inject(fiveDayCityForecastFragment: FiveDayCityForecastFragment)

    fun inject(summaryFragment: SummaryFragment)
}