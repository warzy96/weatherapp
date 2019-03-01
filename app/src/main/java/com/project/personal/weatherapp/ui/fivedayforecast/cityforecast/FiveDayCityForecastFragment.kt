package com.project.personal.weatherapp.ui.fivedayforecast.cityforecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.personal.weatherapp.R
import com.project.personal.weatherapp.di.fragment.FragmentComponent
import com.project.personal.weatherapp.ui.base.BaseFragment
import com.project.personal.weatherapp.ui.fivedayforecast.FiveDayForecastAdapter
import com.project.personal.weatherapp.ui.fivedayforecast.FiveDayForecastListViewModel
import kotlinx.android.synthetic.main.five_day_forecast_fragment.*
import javax.inject.Inject

class FiveDayCityForecastFragment : BaseFragment(), FiveDayCityForecastContract.View {

    private var cityName: String? = null
    private var cityId: Int? = null

    @Inject
    lateinit var presenter: FiveDayCityForecastPresenter

    @Inject
    lateinit var fiveDayForecastAdapter: FiveDayForecastAdapter

    companion object {
        const val TAG = "FiveDayCityForecastFragment"
        const val CITY_NAME_BUNDLE_KEY = "cityName"
        const val CITY_ID_BUNDLE_KEY = "cityId"

        fun newInstance(): FiveDayCityForecastFragment {
            return FiveDayCityForecastFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cityName = arguments?.get(CITY_NAME_BUNDLE_KEY).toString()
        cityId = arguments?.getInt(CITY_ID_BUNDLE_KEY)
        presenter.setView(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.five_day_forecast_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initSwipeRefreshLayout()
    }

    private fun initSwipeRefreshLayout() {
        five_day_forecast_refresh_layout.isRefreshing = true
        presenter.start(id)
    }

    private fun initRecyclerView() {
        five_day_forecast_recycler_view.adapter = fiveDayForecastAdapter
        five_day_forecast_recycler_view.layoutManager = LinearLayoutManager(context)
    }

    override fun render(fiveDayForecastListViewModel: FiveDayForecastListViewModel) {
        five_day_forecast_refresh_layout.isRefreshing = false
        fiveDayForecastAdapter.setForecasts(fiveDayForecastListViewModel)
    }

    override fun inject(fragmentComponent: FragmentComponent?) {
        fragmentComponent?.inject(this)
    }
}