package com.project.personal.weatherapp.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.project.personal.weatherapp.di.application.ForApplication
import javax.inject.Inject


class LocationProviderImpl
@Inject
constructor(val locationManager: LocationManager,
            @ForApplication val context: Context) : LocationProvider {

    companion object {
        const val LOCATION_REFRESH_TIME = 10000L
        const val LOCATION_REFRESH_DISTANCE = 2000f
        const val LOCATION_REQUEST_CODE = 99
    }

    private lateinit var currentActivity: Activity
    private var latitude: Double? = null
    private var longitude: Double? = null

    private val weatherLocationListener = object : LocationListener {
        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        }

        override fun onProviderEnabled(provider: String?) {
        }

        override fun onProviderDisabled(provider: String?) {
        }

        override fun onLocationChanged(location: Location) {
            longitude = location.longitude
            latitude = location.latitude
        }
    }

    override fun provideLocationCity(): com.project.personal.data.Location {
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    LOCATION_REFRESH_TIME, LOCATION_REFRESH_DISTANCE, weatherLocationListener)
        } else {
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(currentActivity,
                            Manifest.permission.ACCESS_COARSE_LOCATION)) {

                AlertDialog.Builder(currentActivity)
                        .setTitle("Location permission")
                        .setMessage("Please enable device permission so we could show your local weather.")
                        .setPositiveButton("OK") { _, _ ->
                            //Prompt the user once explanation has been shown
                            ActivityCompat.requestPermissions(currentActivity,
                                    arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                                    LOCATION_REQUEST_CODE)
                        }
                        .create()
                        .show()
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(currentActivity,
                        arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), LOCATION_REQUEST_CODE)
            }
        }
        Log.d("location", "latitude = $latitude, longitude = $longitude")
        return com.project.personal.data.Location(latitude, longitude)
    }

    override fun setCurrentActivity(activity: Activity) {
        this.currentActivity = activity
    }
}