package com.project.personal.weatherapp.ui

import android.os.Bundle
import android.view.Menu
import com.facebook.stetho.Stetho
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

        Stetho.initializeWithDefaults(this)

        setSupportActionBar(navigationToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        router.showFiveDayForecastScreen()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)

        val searchMenuItem = menu?.findItem(R.id.action_search_cities)
        searchMenuItem?.setOnMenuItemClickListener {
            router.showSearchCityScreen()
            searchMenuItem.expandActionView()
            true
        }

        return true
    }

    override fun inject(activityComponent: ActivityComponent?) {
        activityComponent?.inject(this)
    }
}
