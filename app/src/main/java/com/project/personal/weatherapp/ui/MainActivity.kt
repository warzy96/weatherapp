package com.project.personal.weatherapp.ui

import android.os.Bundle
import com.project.personal.weatherapp.R
import com.project.personal.weatherapp.di.activity.ActivityComponent
import com.project.personal.weatherapp.di.activity.DaggerActivity
import com.project.personal.weatherapp.router.Router
import com.project.personal.weatherapp.util.LocationProviderImpl
import javax.inject.Inject

class MainActivity : DaggerActivity() {

    @Inject
    lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        router.showFiveDayForecastScreen()
    }

    override fun inject(activityComponent: ActivityComponent?) {
        activityComponent?.inject(this)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == LocationProviderImpl.PERMISSION_ACCESS_COURSE_LOCATION_REQUEST_CODE) {
            //TODO
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
