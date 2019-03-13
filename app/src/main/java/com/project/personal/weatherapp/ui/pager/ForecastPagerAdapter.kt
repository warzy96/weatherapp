package com.project.personal.weatherapp.ui.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.project.personal.domain.model.City
import com.project.personal.weatherapp.R
import com.project.personal.weatherapp.ui.fivedayforecast.FiveDayForecastLocationFragment
import com.project.personal.weatherapp.ui.fivedayforecast.cityforecast.FiveDayCityForecastFragment
import com.project.personal.weatherapp.util.StringUtil

class ForecastPagerAdapter(stringUtil: StringUtil, fragmentManager: FragmentManager)
    : FragmentStatePagerAdapter(fragmentManager), PagerContract.Adapter {

    private val titles = ArrayList<String>()
    private val fragmentSuppliers = ArrayList<Fragment>()

    init {
        fragmentSuppliers.add(FiveDayForecastLocationFragment.newInstance())
        titles.add(stringUtil.getStringResource(R.string.title_location_weather))
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
        return titles[position]
    }

    fun addItem(fragment: Fragment, title: String) {
        //Do not add a city if it already exists in viewpager
        if (titles.contains(title)) return

        fragmentSuppliers.add(fragment)
        titles.add(title)
        notifyDataSetChanged()
    }

    override fun update(cities: List<City>) {
        cities.forEach {
            addItem(FiveDayCityForecastFragment.newInstance(it.title, it.woeid), it.title)
        }
    }
}