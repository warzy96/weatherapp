package com.project.personal.weatherapp.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.personal.weatherapp.R
import com.project.personal.weatherapp.di.fragment.FragmentComponent
import com.project.personal.weatherapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.search_layout.*
import javax.inject.Inject


class SearchFragment : BaseFragment(), SearchContract.View {

    companion object {
        const val TAG = "SearchFragment"
        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
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

    @Inject
    lateinit var presenter: SearchPresenter

    @Inject
    lateinit var searchAdapter: SearchAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        presenter.setView(this)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        val searchItem = menu.findItem(R.id.action_search_cities)
        val searchView = searchItem?.actionView as SearchView
        searchItem.setOnMenuItemClickListener(null)
        searchView.setOnQueryTextListener(listener)
        searchItem.expandActionView()
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.search_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchAdapter.setFragmentManager(fragmentManager!!)
        searchAdapter.setPresenter(presenter)
        initRecyclerView()
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
}