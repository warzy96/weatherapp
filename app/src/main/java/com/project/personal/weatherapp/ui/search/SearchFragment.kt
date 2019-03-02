package com.project.personal.weatherapp.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.personal.weatherapp.R
import com.project.personal.weatherapp.di.fragment.FragmentComponent
import com.project.personal.weatherapp.ui.base.BaseFragment
import com.project.personal.weatherapp.ui.pager.ForecastPagerAdapter
import kotlinx.android.synthetic.main.search_layout.*
import javax.inject.Inject


class SearchFragment : BaseFragment(), SearchContract.View {

    companion object {
        const val TAG = "SearchFragment"
        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }

    @Inject
    lateinit var presenter: SearchPresenter

    @Inject
    lateinit var searchAdapter: SearchAdapter

    lateinit var forecastPagerAdapter: ForecastPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.setView(this)
        searchAdapter.forecastPagerAdapter = forecastPagerAdapter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.search_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        searchView.setOnQueryTextListener(listener)
    }

    private fun initRecyclerView() {
        searchItemsRecyclerView.layoutManager = LinearLayoutManager(context)
        searchItemsRecyclerView.adapter = searchAdapter
    }

    override fun render(searchItemsViewModel: SearchItemsViewModel) {
        searchAdapter.setResults(searchItemsViewModel)
    }

    override fun inject(fragmentComponent: FragmentComponent?) {
        fragmentComponent?.inject(this)
    }

    fun setPagerAdapter(forecastPagerAdapter: ForecastPagerAdapter) {
        this.forecastPagerAdapter = forecastPagerAdapter
    }

    private val listener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextChange(newText: String?): Boolean {
            return false
        }

        override fun onQueryTextSubmit(query: String?): Boolean {
            presenter.start(query!!)
            return false
        }
    }
}