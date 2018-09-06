package com.project.personal.weatherapp.di.application

import com.project.personal.weatherapp.app.WeatherApplication
import com.project.personal.weatherapp.di.activity.ActivityComponent
import com.project.personal.weatherapp.di.application.module.ApplicationModule
import com.project.personal.weatherapp.di.application.module.DataModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            ApplicationModule::class,
            DataModule::class
        ]
)
interface ApplicationComponent : ApplicationComponentInjects {

    class Initializer private constructor() {

        companion object {
            fun init(weatherApplication: WeatherApplication): ApplicationComponent {
                return DaggerApplicationComponent.builder()
                        .applicationModule(ApplicationModule(weatherApplication))
                        .build()
            }
        }
    }

    fun activityComponentBuilder(): ActivityComponent.Builder
}