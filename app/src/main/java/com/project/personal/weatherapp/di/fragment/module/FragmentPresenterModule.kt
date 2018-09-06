package com.project.personal.weatherapp.di.fragment.module

import com.project.personal.weatherapp.di.fragment.DaggerFragment
import com.project.personal.weatherapp.di.fragment.FragmentComponent
import dagger.Module

@Module
class FragmentPresenterModule(private val daggerFragment: DaggerFragment) {

    private fun getFragmentComponent(): FragmentComponent? {
        return daggerFragment.fragmentComponent
    }
}