package com.project.personal.weatherapp.di.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.project.personal.weatherapp.di.ComponentFactory
import com.project.personal.weatherapp.di.activity.DaggerActivity

abstract class DaggerFragment : Fragment() {

    var fragmentComponent: FragmentComponent? = null
        get() {
            return if (field == null) {
                ComponentFactory.createFragmentComponent(this, getDaggerActivity()?.getActivityComponent())
            } else {
                field
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject(fragmentComponent)
    }

    protected abstract fun inject(fragmentComponent: FragmentComponent?)

    private fun getDaggerActivity(): DaggerActivity? {
        return super.getActivity() as DaggerActivity
    }
}