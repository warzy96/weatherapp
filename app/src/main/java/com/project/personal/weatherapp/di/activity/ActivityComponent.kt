package com.project.personal.weatherapp.di.activity

import com.project.personal.weatherapp.di.activity.module.ActivityModule
import com.project.personal.weatherapp.di.application.ApplicationComponent
import com.project.personal.weatherapp.di.fragment.FragmentComponent
import dagger.Subcomponent

@Subcomponent(
        modules = [
            ActivityModule::class
        ]
)
interface ActivityComponent : ActivityComponentInjects {

    @Subcomponent.Builder
    interface Builder {

        fun activityModule(activityModule: ActivityModule): Builder

        fun build(): ActivityComponent
    }

    class Initializer private constructor() {

        companion object {
            fun init(daggerActivity: DaggerActivity, applicationComponent: ApplicationComponent?): ActivityComponent? {
                return applicationComponent?.activityComponentBuilder()
                        ?.activityModule(ActivityModule(daggerActivity))
                        ?.build()
            }
        }
    }

    fun fragmentComponentBuilder(): FragmentComponent.Builder
}