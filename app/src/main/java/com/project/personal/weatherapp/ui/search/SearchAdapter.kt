package com.project.personal.weatherapp.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.project.personal.domain.model.City
import com.project.personal.weatherapp.R
import kotlinx.android.synthetic.main.search_item_layout.view.*
import javax.inject.Inject

class SearchAdapter
@Inject
constructor(val layoutInflater: LayoutInflater) :
        RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private lateinit var fragmentManager: FragmentManager
    private var searchResults = ArrayList<SearchItemViewModel>()
    private lateinit var presenter: SearchPresenter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(layoutInflater.inflate(R.layout.search_item_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return searchResults.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.render(searchResults[position], fragmentManager, presenter)
    }

    fun setResults(searchItemsViewModel: SearchItemsViewModel) {
        searchResults = searchItemsViewModel.searchItems
        notifyDataSetChanged()
    }

    fun setFragmentManager(fragmentManager: FragmentManager) {
        this.fragmentManager = fragmentManager
    }

    fun setPresenter(presenter: SearchPresenter) {
        this.presenter = presenter
    }

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun render(searchItemViewModel: SearchItemViewModel, fragmentManager: FragmentManager,
                   presenter: SearchPresenter) {
            itemView.cityNameTextView.text = searchItemViewModel.cityName
            itemView.setOnClickListener {
                fragmentManager.popBackStackImmediate()
                presenter.saveCity(City(searchItemViewModel.cityName, searchItemViewModel.woeid))
            }
        }
    }
}