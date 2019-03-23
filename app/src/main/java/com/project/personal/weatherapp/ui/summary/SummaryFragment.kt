package com.project.personal.weatherapp.ui.summary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.personal.weatherapp.R
import com.project.personal.weatherapp.di.fragment.FragmentComponent
import com.project.personal.weatherapp.ui.base.BaseFragment
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import kotlinx.android.synthetic.main.cities_list_layout.*
import javax.inject.Inject

class SummaryFragment : BaseFragment(), SummaryContract.View {

    @Inject
    lateinit var summaryAdapter: SummaryAdapter

    @Inject
    lateinit var presenter: SummaryPresenter

    companion object {
        const val TAG = "SummaryFragment"

        fun newInstance(): SummaryFragment {
            return SummaryFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.setView(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.cities_list_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initSwipeRefreshLayout()
    }

    override fun onResume() {
        super.onResume()
        if (main_swipe != null) {
            main_swipe.isRefreshing = true
            presenter.start()
        }
    }

    private fun initRecyclerView() {
        citiesRecyclerView.adapter = summaryAdapter
        citiesRecyclerView.itemAnimator = SlideInUpAnimator()
        val layoutManager = LinearLayoutManager(citiesRecyclerView.context)
        citiesRecyclerView.layoutManager = layoutManager
    }

    private fun initSwipeRefreshLayout() {
        main_swipe.setWaveColor(ResourcesCompat.getColor(resources, R.color.colorAccent, null))
        main_swipe.setOnRefreshListener {
            main_swipe.isRefreshing = true
            presenter.start()
        }
    }

    override fun render(citySummaryListViewModel: CitySummaryListViewModel) {
        if (main_swipe != null) {
            main_swipe.isRefreshing = false
        }
        summaryAdapter.setCities(citySummaryListViewModel)
        citiesRecyclerView?.scheduleLayoutAnimation()
        citiesRecyclerView?.animate()
    }

    override fun inject(fragmentComponent: FragmentComponent?) {
        fragmentComponent?.inject(this)
    }
}