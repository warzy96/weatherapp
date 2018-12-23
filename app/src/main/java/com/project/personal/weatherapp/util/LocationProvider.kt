package com.project.personal.weatherapp.util

import android.app.Activity
import com.project.personal.data.Location

interface LocationProvider {

    fun provideLocationCity(): Location

    fun setCurrentActivity(activity: Activity)
}