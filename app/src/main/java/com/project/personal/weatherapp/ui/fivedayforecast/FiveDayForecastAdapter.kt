package com.project.personal.weatherapp.ui.fivedayforecast

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.personal.weatherapp.R
import com.project.personal.weatherapp.util.ImageLoader
import com.project.personal.weatherapp.util.parseImageUrl
import kotlinx.android.synthetic.main.day_of_week.view.*
import javax.inject.Inject

class FiveDayForecastAdapter
@Inject
constructor(val layoutInflater: LayoutInflater, val imageLoader: ImageLoader)
    : RecyclerView.Adapter<FiveDayForecastAdapter.FiveDayForecastViewHolder>() {

    private val forecasts = ArrayList<FiveDayForecastViewModel>()

    override fun getItemCount(): Int {
        return forecasts.size
    }

    override fun onBindViewHolder(holder: FiveDayForecastViewHolder, position: Int) {
        holder.render(forecasts[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FiveDayForecastViewHolder {
        return FiveDayForecastViewHolder(layoutInflater.inflate(
                R.layout.day_of_week, parent, false), imageLoader)
    }

    fun setForecasts(fiveDayForecastListViewModel: FiveDayForecastListViewModel) {
        forecasts.clear()
        forecasts.addAll(fiveDayForecastListViewModel.forecasts)
        notifyDataSetChanged()
    }


    class FiveDayForecastViewHolder
    constructor(itemView: View, private val imageLoader: ImageLoader)
        : RecyclerView.ViewHolder(itemView) {

        fun render(fiveDayForecastViewModel: FiveDayForecastViewModel) {
            itemView.dayOfWeekCurrentTemperatureText.text =
                    String.format("%.2f", fiveDayForecastViewModel.currentTemp)
            itemView.dayOfWeekHumidityText.text = fiveDayForecastViewModel.humidity.toString()
            itemView.dayOfWeekWeatherStateText.text = fiveDayForecastViewModel.weatherState
            itemView.dayOfWeekWindDirection.text = fiveDayForecastViewModel.windDirection
            itemView.dayOfWeekWindSpeed.text =
                    String.format("%.2f", fiveDayForecastViewModel.windSpeed)
            imageLoader.renderImage(parseImageUrl(fiveDayForecastViewModel.weatherStateAbbr),
                    itemView.dayOfWeekWeatherIcon)
        }
    }
}