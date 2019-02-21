package com.project.personal.weatherapp.util

import com.project.personal.data.Location

interface LocationProvider {

    fun provideLocationCity(): Location
}