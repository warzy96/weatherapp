package com.project.personal.weatherapp.ui.fivedayforecast

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.personal.data.network.configuration.Urls.ICONS_BASE_URL
import com.project.personal.domain.model.WeatherModel
import com.project.personal.weatherapp.R
import com.project.personal.weatherapp.di.fragment.FragmentComponent
import com.project.personal.weatherapp.ui.base.BaseFragment
import com.project.personal.weatherapp.util.ImageLoader
import kotlinx.android.synthetic.main.day_of_week.*
import javax.inject.Inject

class FiveDayForecastFragment : BaseFragment(), FiveDayForecastContract.View {

    @Inject
    lateinit var presenter: FiveDayForecastContract.Presenter

    @Inject
    lateinit var imageLoader: ImageLoader

    companion object {
        const val TAG = "FiveDayForecastFragment"
        fun newInstance(): FiveDayForecastFragment {
            return FiveDayForecastFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //TODO: Dynamically solve this
        presenter.setView(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.day_of_week, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.start(1105779)
    }

    override fun render(weatherModel: WeatherModel) {
        val consolidatedWeather = weatherModel.weatherDetailsList.first()
        dayOfWeekCurrentTemperatureText.text = String.format("%.2f", consolidatedWeather.currentTemp)
        dayOfWeekHumidityText.text = consolidatedWeather.humidity.toString()
        dayOfWeekWeatherStateText.text = consolidatedWeather.weatherState
        dayOfWeekWindDirection.text = consolidatedWeather.windDirection
        dayOfWeekWindSpeed.text = String.format("%.2f", consolidatedWeather.windSpeed)
        imageLoader.renderImage(ICONS_BASE_URL +
                consolidatedWeather.weatherStateAbbr + ".png", dayOfWeekWeatherIcon)
        Log.d("myData", ICONS_BASE_URL + consolidatedWeather.weatherStateAbbr + ".png")
    }

    override fun inject(fragmentComponent: FragmentComponent?) {
        fragmentComponent!!.inject(this)
    }
}