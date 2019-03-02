package com.project.personal.weatherapp.ui.pager

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.project.personal.weatherapp.R
import com.project.personal.weatherapp.ui.fivedayforecast.FiveDayForecastLocationFragment
import com.project.personal.weatherapp.util.StringUtil

class ForecastPagerAdapter(stringUtil: StringUtil, fragmentManager: FragmentManager)
    : FragmentPagerAdapter(fragmentManager) {

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
        Log.d("adapter getItem", fragmentSuppliers.size.toString())
        return fragmentSuppliers[position]
    }

    override fun getCount(): Int {
        Log.d("adapter getCount", fragmentSuppliers.size.toString())
        return fragmentSuppliers.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        Log.d("adapter getPageTitle", fragmentSuppliers.size.toString())
        return titles[position]
    }

    fun addItem(fragment: Fragment, title: String) {
        Log.d("adapter addItem", fragmentSuppliers.size.toString())
        Log.d("adapter reference", this.toString())
        fragmentSuppliers.add(fragment)
        titles.add(title)
        notifyDataSetChanged()
    }
}