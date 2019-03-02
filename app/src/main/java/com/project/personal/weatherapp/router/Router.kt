package com.project.personal.weatherapp.router

import android.app.Activity
import com.project.personal.weatherapp.R
import com.project.personal.weatherapp.ui.pager.ActivityFragment
import com.project.personal.weatherapp.ui.pager.ForecastPagerAdapter
import com.project.personal.weatherapp.ui.search.SearchFragment
import javax.inject.Inject

class Router
@Inject
constructor(private val activity: Activity, private val fragmentManager: androidx.fragment.app.FragmentManager) {

    fun showFiveDayForecastScreen() {
        fragmentManager.beginTransaction()
                .add(CONTAINER_ID, ActivityFragment.newInstance(), ActivityFragment.TAG)
                .commit()
    }

    fun showSearchCityScreen(forecastPagerAdapter: ForecastPagerAdapter) {
        val searchFragment = SearchFragment.newInstance()
        searchFragment.setPagerAdapter(forecastPagerAdapter)
        fragmentManager.beginTransaction()
                .replace(CONTAINER_ID, searchFragment, SearchFragment.TAG)
                .addToBackStack(null)
                .commit()
    }

    companion object {
        const val CONTAINER_ID = R.id.fragment_container
    }
}