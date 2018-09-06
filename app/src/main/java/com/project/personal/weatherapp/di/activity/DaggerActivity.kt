package com.project.personal.weatherapp.di.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.project.personal.weatherapp.app.WeatherApplication
import com.project.personal.weatherapp.di.ComponentFactory

abstract class DaggerActivity : AppCompatActivity() {

    private var activityComponent: ActivityComponent? = null

    private val weatherApplication: WeatherApplication
        get() = WeatherApplication.from(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject(getActivityComponent())
    }

    fun getActivityComponent(): ActivityComponent? {
        activityComponent?.let {
            return activityComponent
        } ?: run {
            return ComponentFactory.createActivityComponent(this, weatherApplication.getApplicationComponent())
        }
    }

    protected abstract fun inject(activityComponent: ActivityComponent?)
}