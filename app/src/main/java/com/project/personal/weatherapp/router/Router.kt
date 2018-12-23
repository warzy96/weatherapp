package com.project.personal.weatherapp.router

import android.app.Activity
import androidx.fragment.app.FragmentManager
import com.project.personal.weatherapp.R
import com.project.personal.weatherapp.ui.fivedayforecast.FiveDayForecastFragment
import javax.inject.Inject

class Router @Inject constructor(private val activity: Activity, private val fragmentManager: androidx.fragment.app.FragmentManager) {

    private val CONTAINER_ID = R.id.fragment_container

    fun showFiveDayForecastScreen() {
        fragmentManager.beginTransaction()
                .add(CONTAINER_ID, FiveDayForecastFragment.newInstance(), FiveDayForecastFragment.TAG)
                .commit()
    }
}