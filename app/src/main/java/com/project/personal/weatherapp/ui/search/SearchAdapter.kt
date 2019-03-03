package com.project.personal.weatherapp.ui.search

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
constructor(val layoutInflater: LayoutInflater) :
        RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    lateinit var forecastPagerAdapter: ForecastPagerAdapter
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
                val newFragment = FiveDayCityForecastFragment.newInstance(searchItemViewModel.cityName,
                        searchItemViewModel.woeid)
                forecastPagerAdapter.addItem(newFragment, searchItemViewModel.cityName)
            }
        }
    }
}