package com.project.personal.weatherapp.ui.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.project.personal.weatherapp.R
import com.project.personal.weatherapp.ui.fivedayforecast.FiveDayForecastFragment
import com.project.personal.weatherapp.util.StringUtil

class ForecastPagerAdapter(val stringUtil: StringUtil, fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private val titles = ArrayList<Int>()
    private val fragmentSuppliers = ArrayList<Fragment>()

    init {
        fragmentSuppliers.add(FiveDayForecastFragment.newInstance())
        titles.add(R.string.title_location_weather)
    }

    override fun getItem(position: Int): Fragment {
        if (position > fragmentSuppliers.size - 1) {
            throw RuntimeException("Position is greater than the number of items!")
        }
        return fragmentSuppliers[position]
    }

    override fun getCount(): Int {
        return fragmentSuppliers.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return stringUtil.getStringResource(titles[position])
    }
}