package com.project.personal.weatherapp.di.fragment

import com.project.personal.weatherapp.di.activity.ActivityComponent
import com.project.personal.weatherapp.di.fragment.module.FragmentModule
import com.project.personal.weatherapp.di.fragment.module.FragmentPresenterModule
import dagger.Subcomponent

@FragmentScope
@Subcomponent(
        modules = [
        ]
)
interface FragmentComponent : FragmentComponentInjects {

    @Subcomponent.Builder
    interface Builder {

        fun build(): FragmentComponent

    }

    object Initializer {

        fun init(daggerFragment: DaggerFragment, activityComponent: ActivityComponent?): FragmentComponent? {
            return activityComponent?.fragmentComponentBuilder()
                    ?.build()
        }
    }
}