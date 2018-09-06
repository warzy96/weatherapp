package com.project.personal.weatherapp.di

import com.project.personal.weatherapp.app.WeatherApplication
import com.project.personal.weatherapp.di.activity.ActivityComponent
import com.project.personal.weatherapp.di.activity.DaggerActivity
import com.project.personal.weatherapp.di.application.ApplicationComponent
import com.project.personal.weatherapp.di.fragment.DaggerFragment
import com.project.personal.weatherapp.di.fragment.FragmentComponent

object ComponentFactory {

    fun createApplicationComponent(weatherApplication: WeatherApplication): ApplicationComponent {
        return ApplicationComponent.Initializer.init(weatherApplication)
    }

    fun createActivityComponent(daggerActivity: DaggerActivity, applicationComponent: ApplicationComponent?): ActivityComponent? {
        return ActivityComponent.Initializer.init(daggerActivity, applicationComponent)
    }

    fun createFragmentComponent(daggerFragment: DaggerFragment, activityComponent: ActivityComponent?): FragmentComponent? {
        return FragmentComponent.Initializer.init(daggerFragment, activityComponent)
    }
}