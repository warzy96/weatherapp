package com.project.personal.weatherapp.ui.pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.personal.weatherapp.R
import com.project.personal.weatherapp.di.fragment.DaggerFragment
import com.project.personal.weatherapp.di.fragment.FragmentComponent
import com.project.personal.weatherapp.router.Router
import com.project.personal.weatherapp.util.StringUtil
import kotlinx.android.synthetic.main.pager_layout.*
import javax.inject.Inject

class ActivityFragment : DaggerFragment() {

    companion object {
        const val TAG = "ActivityFragment"
        fun newInstance(): ActivityFragment {
            return ActivityFragment()
        }
    }

    lateinit var forecastPagerAdapter: ForecastPagerAdapter

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var stringUtil: StringUtil

    @Inject
    lateinit var forecastPagerPresenter: ForecastPagerPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        forecastPagerAdapter = ForecastPagerAdapter(stringUtil, childFragmentManager)
        forecastPagerPresenter.setAdapter(forecastPagerAdapter)
        forecastPagerPresenter.start()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.pager_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = forecast_view_pager
        viewPager.adapter = forecastPagerAdapter

        val tabLayout = forecast_tab_layout
        tabLayout.setupWithViewPager(viewPager)

        addFragmentButton.setOnClickListener {
            router.showSearchCityScreen(forecastPagerAdapter)
            addFragmentButton.hide()
        }
    }

    override fun inject(fragmentComponent: FragmentComponent?) {
        fragmentComponent?.inject(this)
    }
}