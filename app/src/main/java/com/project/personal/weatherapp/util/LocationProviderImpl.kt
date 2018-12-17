package com.project.personal.weatherapp.util

import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import android.support.v4.content.ContextCompat
import javax.inject.Inject

class LocationProviderImpl
@Inject
constructor(val locationManager: LocationManager,
            val locationListener: LocationListener,
            val context: Context) : LocationProvider {

    companion object {
        const val LOCATION_REFRESH_TIME = 3600000L
        const val LOCATION_REFRESH_DISTANCE = 2000f
    }

    override fun provideLocationCity(): Int {
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    LOCATION_REFRESH_TIME, LOCATION_REFRESH_DISTANCE, locationListener)
        }

        return 0
    }
}