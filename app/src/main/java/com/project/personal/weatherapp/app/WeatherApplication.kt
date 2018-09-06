package com.project.personal.weatherapp.app

import android.app.Application
import android.content.Context
import com.project.personal.weatherapp.di.ComponentFactory
import com.project.personal.weatherapp.di.application.ApplicationComponent

class WeatherApplication : Application() {

    private lateinit var applicationComponent: ApplicationComponent

    companion object {
        fun from(context: Context): WeatherApplication {
            return context.applicationContext as WeatherApplication
        }
    }

    override fun onCreate() {
        super.onCreate()
        initApplicationComponent()
        injectMe()
    }

    private fun injectMe() {
        applicationComponent.inject(this)
    }

    private fun initApplicationComponent() {
        applicationComponent = ComponentFactory.createApplicationComponent(this)
    }

    fun getApplicationComponent(): ApplicationComponent {
        return applicationComponent
    }
}