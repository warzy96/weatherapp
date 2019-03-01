package com.project.personal.weatherapp.ui.search

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.personal.weatherapp.R
import com.project.personal.weatherapp.ui.fivedayforecast.cityforecast.FiveDayCityForecastFragment
import com.project.personal.weatherapp.ui.pager.ForecastPagerAdapter
import kotlinx.android.synthetic.main.search_item_layout.view.*
import javax.inject.Inject

class SearchAdapter
@Inject
constructor(val layoutInflater: LayoutInflater, val forecastPagerAdapter: ForecastPagerAdapter) :
        RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private var searchResults = ArrayList<SearchItemViewModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(layoutInflater.inflate(R.layout.search_item_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return searchResults.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.render(searchResults[position], forecastPagerAdapter)
    }

    fun setResults(searchItemsViewModel: SearchItemsViewModel) {
        searchResults = searchItemsViewModel.searchItems
        notifyDataSetChanged()
    }

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun render(searchItemViewModel: SearchItemViewModel, forecastPagerAdapter: ForecastPagerAdapter) {
            itemView.cityNameTextView.text = searchItemViewModel.cityName
            itemView.setOnClickListener {
                Log.d("myData", forecastPagerAdapter.count.toString())
                val newFragment = FiveDayCityForecastFragment.newInstance()
                newFragment.arguments?.putString(FiveDayCityForecastFragment.CITY_NAME_BUNDLE_KEY, searchItemViewModel
                        .cityName)
                newFragment.arguments?.putInt(FiveDayCityForecastFragment.CITY_ID_BUNDLE_KEY, searchItemViewModel.woeid)
                forecastPagerAdapter.addItem(newFragment, searchItemViewModel.cityName)
                Log.d("myData", forecastPagerAdapter.count.toString())
            }
        }
    }
}