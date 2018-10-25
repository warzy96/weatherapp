package com.project.personal.weatherapp.di.fragment

import com.project.personal.weatherapp.di.activity.ActivityComponent
import com.project.personal.weatherapp.di.fragment.module.FragmentModule
import com.project.personal.weatherapp.di.fragment.module.FragmentPresenterModule
import dagger.Subcomponent

@FragmentScope
@Subcomponent(
        modules = [
            FragmentPresenterModule::class,
            FragmentModule::class
        ]
)
interface FragmentComponent : FragmentComponentInjects {

    @Subcomponent.Builder
    interface Builder {

        fun build(): FragmentComponent

        fun fragmentPresenterModule(fragmentPresenterModule: FragmentPresenterModule): Builder

        fun fragmentModule(fragmentModule: FragmentModule): Builder
    }

    object Initializer {

        fun init(daggerFragment: DaggerFragment, activityComponent: ActivityComponent?): FragmentComponent? {
            return activityComponent?.fragmentComponentBuilder()
                    ?.fragmentModule(FragmentModule(daggerFragment))
                    ?.fragmentPresenterModule(FragmentPresenterModule(daggerFragment))
                    ?.build()
        }
    }
}