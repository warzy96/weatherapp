package com.project.personal.weatherapp.router

import android.app.Activity
import com.project.personal.weatherapp.R
import com.project.personal.weatherapp.ui.fivedayforecast.FiveDayForecastFragment
import com.project.personal.weatherapp.ui.pager.ActivityFragment
import com.project.personal.weatherapp.ui.search.SearchFragment
import javax.inject.Inject

class Router
@Inject
constructor(private val activity: Activity, private val fragmentManager: androidx.fragment.app.FragmentManager) {

    fun showFiveDayForecastScreen() {
        fragmentManager.beginTransaction()
                .add(CONTAINER_ID, ActivityFragment.newInstance(), FiveDayForecastFragment.TAG)
                .commit()
    }

    fun showSearchCityScreen() {
        fragmentManager.beginTransaction()
                .replace(CONTAINER_ID, SearchFragment.newInstance(), SearchFragment.TAG)
                .addToBackStack(null)
                .commit()
    }

    companion object {
        const val CONTAINER_ID = R.id.fragment_container
    }
}