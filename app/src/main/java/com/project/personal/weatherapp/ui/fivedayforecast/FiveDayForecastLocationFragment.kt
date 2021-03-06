package com.project.personal.weatherapp.ui.fivedayforecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.personal.weatherapp.R
import com.project.personal.weatherapp.di.fragment.FragmentComponent
import com.project.personal.weatherapp.ui.base.BaseFragment
import com.project.personal.weatherapp.util.LocationProvider
import kotlinx.android.synthetic.main.five_day_forecast_fragment.*
import javax.inject.Inject

class FiveDayForecastLocationFragment : BaseFragment(), FiveDayForecastContract.View {

    @Inject
    lateinit var fiveDayForecastAdapter: FiveDayForecastAdapter

    @Inject
    lateinit var locationProvider: LocationProvider

    @Inject
    lateinit var presenter: FiveDayForecastPresenter

    companion object {
        const val TAG = "FiveDayForecastLocationFragment"
        fun newInstance(): FiveDayForecastLocationFragment {
            return FiveDayForecastLocationFragment()
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
    }

    override fun onResume() {
        super.onResume()
        if (five_day_forecast_refresh_layout != null) {
            presenter.start(locationProvider.provideLocationCity())
            five_day_forecast_refresh_layout.isRefreshing = true
        }
    }

    private fun initSwipeRefreshLayout() {
        five_day_forecast_refresh_layout.setOnRefreshListener {
            five_day_forecast_refresh_layout.isRefreshing = true
            presenter.start(locationProvider.provideLocationCity())
        }
    }

    private fun initRecyclerView() {
        five_day_forecast_recycler_view.adapter = fiveDayForecastAdapter
        five_day_forecast_recycler_view.layoutManager = LinearLayoutManager(context)
    }

    override fun render(fiveDayForecastListViewModel: FiveDayForecastListViewModel) {
        if (five_day_forecast_refresh_layout != null) {
            five_day_forecast_refresh_layout.isRefreshing = false
        }
        fiveDayForecastAdapter.setForecasts(fiveDayForecastListViewModel)

    }

    override fun inject(fragmentComponent: FragmentComponent?) {
        fragmentComponent?.inject(this)
    }
}