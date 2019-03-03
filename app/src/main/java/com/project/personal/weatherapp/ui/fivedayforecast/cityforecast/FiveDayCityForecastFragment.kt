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

    private lateinit var cityName: String
    private var cityId: Int? = null

    @Inject
    lateinit var presenter: FiveDayCityForecastPresenter

    @Inject
    lateinit var fiveDayForecastAdapter: FiveDayForecastAdapter

    companion object {
        const val TAG = "FiveDayCityForecastFragment"

        fun newInstance(cityName: String, id: Int): FiveDayCityForecastFragment {
            val fragment = FiveDayCityForecastFragment()
            fragment.setCityNameAndId(cityName, id)
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

    override fun onResume() {
        super.onResume()
        presenter.start(cityId!!)
        five_day_forecast_refresh_layout.isRefreshing = true
    }

    private fun initSwipeRefreshLayout() {
        five_day_forecast_refresh_layout.setOnRefreshListener {
            five_day_forecast_refresh_layout.isRefreshing = true
            presenter.start(cityId!!)
        }
    }

    private fun initRecyclerView() {
        five_day_forecast_recycler_view.adapter = fiveDayForecastAdapter
        five_day_forecast_recycler_view.layoutManager = LinearLayoutManager(context)
    }

    private fun setCityNameAndId(cityName: String, id: Int) {
        this.cityName = cityName
        this.cityId = id
    }

    override fun render(fiveDayForecastListViewModel: FiveDayForecastListViewModel) {
        five_day_forecast_refresh_layout.isRefreshing = false
        fiveDayForecastAdapter.setForecasts(fiveDayForecastListViewModel)
    }

    override fun inject(fragmentComponent: FragmentComponent?) {
        fragmentComponent?.inject(this)
    }
}