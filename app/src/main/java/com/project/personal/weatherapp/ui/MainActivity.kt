package com.project.personal.weatherapp.ui

import android.os.Bundle
import com.project.personal.weatherapp.R
import com.project.personal.weatherapp.di.activity.ActivityComponent
import com.project.personal.weatherapp.di.activity.DaggerActivity
import com.project.personal.weatherapp.router.Router
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerActivity() {

    @Inject
    lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragmentButton.setOnClickListener {
            addFragmentButton.hide()
            router.showSearchCityScreen()
        }
        router.showFiveDayForecastScreen()
    }

    override fun onBackPressed() {
        addFragmentButton.show()
        super.onBackPressed()
    }

    override fun inject(activityComponent: ActivityComponent?) {
        activityComponent?.inject(this)
    }
}
