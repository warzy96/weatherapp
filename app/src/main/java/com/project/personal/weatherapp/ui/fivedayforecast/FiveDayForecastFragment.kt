package com.project.personal.weatherapp.ui.fivedayforecast

import android.location.LocationManager
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.personal.weatherapp.R
import com.project.personal.weatherapp.di.fragment.FragmentComponent
import com.project.personal.weatherapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.five_day_forecast_fragment.*
import javax.inject.Inject

class FiveDayForecastFragment : BaseFragment(), FiveDayForecastContract.View {

    @Inject
    lateinit var presenter: FiveDayForecastContract.Presenter

    @Inject
    lateinit var fiveDayForecastAdapter: FiveDayForecastAdapter

    @Inject
    lateinit var locationManager: LocationManager

    companion object {
        const val TAG = "FiveDayForecastFragment"
        fun newInstance(): FiveDayForecastFragment {
            return FiveDayForecastFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.setView(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.five_day_forecast_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initSwipeRefreshLayout()
        presenter.start(680564)
    }

    private fun initSwipeRefreshLayout() {
        five_day_forecast_refresh_layout.setOnRefreshListener {
            five_day_forecast_refresh_layout.isRefreshing = true
            presenter.start(680564)
        }
    }

    private fun initRecyclerView() {
        five_day_forecast_recycler_view.adapter = fiveDayForecastAdapter
        five_day_forecast_recycler_view.layoutManager = LinearLayoutManager(context)
    }

    private fun initLocationManager() {
    }

    override fun render(fiveDayForecastListViewModel: FiveDayForecastListViewModel) {
        five_day_forecast_refresh_layout.isRefreshing = false
        fiveDayForecastAdapter.setForecasts(fiveDayForecastListViewModel)

    }

    override fun inject(fragmentComponent: FragmentComponent?) {
        fragmentComponent?.inject(this)
    }
}