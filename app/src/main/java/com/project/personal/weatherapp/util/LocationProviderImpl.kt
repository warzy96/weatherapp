package com.project.personal.weatherapp.util

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices


class LocationProviderImpl
constructor(val activity: AppCompatActivity) : LocationProvider {

    companion object {
        const val PERMISSION_ACCESS_COURSE_LOCATION_REQUEST_CODE = 99
    }

    private var latitude: Double? = null
    private var longitude: Double? = null

    override fun provideLocationCity(): com.project.personal.data.Location {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                    PERMISSION_ACCESS_COURSE_LOCATION_REQUEST_CODE)
        }
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)

        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            if (location != null) {
                latitude = location.latitude;
                longitude = location.longitude;
            }
        }
        return com.project.personal.data.Location(latitude, longitude)
    }
}