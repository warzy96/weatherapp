package com.project.personal.weatherapp.ui.fivedayforecast

import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.project.personal.weatherapp.R
import com.project.personal.weatherapp.util.ImageLoader
import com.project.personal.weatherapp.util.generateColorsInHex
import com.project.personal.weatherapp.util.parseImageUrl
import kotlinx.android.synthetic.main.day_of_week.view.*
import java.time.LocalDate
import javax.inject.Inject
import kotlin.math.roundToInt

class FiveDayForecastAdapter
@Inject
constructor(val layoutInflater: LayoutInflater, val imageLoader: ImageLoader)
    : RecyclerView.Adapter<FiveDayForecastAdapter.FiveDayForecastViewHolder>() {

    private val forecasts = ArrayList<FiveDayForecastViewModel>()
    private val colors = generateColorsInHex(ContextCompat.getColor(this.layoutInflater.context, R.color.colorPrimaryDark),
            0.3f, 10)

    override fun getItemCount(): Int {
        return forecasts.size
    }

    override fun onBindViewHolder(holder: FiveDayForecastViewHolder, position: Int) {
        holder.render(forecasts[position], position, colors)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FiveDayForecastViewHolder {
        return FiveDayForecastViewHolder(layoutInflater.inflate(
                R.layout.day_of_week_small, parent, false), imageLoader)
    }

    fun setForecasts(fiveDayForecastListViewModel: FiveDayForecastListViewModel) {
        forecasts.clear()
        forecasts.addAll(fiveDayForecastListViewModel.forecasts)
        notifyDataSetChanged()
    }


    class FiveDayForecastViewHolder
    constructor(itemView: View, private val imageLoader: ImageLoader)
        : RecyclerView.ViewHolder(itemView) {

        private var isViewExpanded = false

        fun render(fiveDayForecastViewModel: FiveDayForecastViewModel, position: Int, colors: IntArray) {
            setColor(position, colors)

            populateViews(fiveDayForecastViewModel)

            //Collapse any opened items on render
            val smallConstraintSet = ConstraintSet()
            smallConstraintSet.clone(itemView.context, R.layout.day_of_week_small)
            animateItemView(smallConstraintSet, itemView.dayOfWeekConstraintLayout)

            itemView.setOnClickListener {
                val smallItemConstraint = ConstraintSet()
                smallItemConstraint.clone(itemView.context, R.layout.day_of_week_small)
                val largeItemConstraint = ConstraintSet()
                largeItemConstraint.clone(itemView.context, R.layout.day_of_week)

                val constraintToApply = if (isViewExpanded) smallItemConstraint else
                    largeItemConstraint

                animateItemView(constraintToApply, itemView.dayOfWeekConstraintLayout)

                if (!isViewExpanded) {
                    itemView.dayOfWeekWeatherIcon.visibility = View.VISIBLE
                } else {
                    itemView.dayOfWeekWeatherIcon.visibility = View.GONE
                }

                isViewExpanded = !isViewExpanded
            }
        }

        private fun animateItemView(constraintToApply: ConstraintSet,
                                    constraintLayout: ConstraintLayout) {
            TransitionManager.beginDelayedTransition(constraintLayout)
            constraintToApply.applyTo(constraintLayout)
        }

        private fun populateViews(fiveDayForecastViewModel: FiveDayForecastViewModel) {
            itemView.dayOfWeekText.text = LocalDate.parse(fiveDayForecastViewModel.applicableDate)
                    .dayOfWeek
                    .name
            itemView.dayOfWeekCurrentTemperatureText.text = fiveDayForecastViewModel.currentTemp
                    .roundToInt().toString()
            itemView.dayOfWeekHumidityText.text = fiveDayForecastViewModel.humidity.toString()
            itemView.dayOfWeekWeatherStateText.text = fiveDayForecastViewModel.weatherState
            itemView.dayOfWeekWindDirection.text = fiveDayForecastViewModel.windDirection
            itemView.dayOfWeekWindSpeed.text =
                    String.format("%.2f", fiveDayForecastViewModel.windSpeed)
            imageLoader.renderImage(parseImageUrl(fiveDayForecastViewModel
                    .weatherStateAbbr), itemView.dayOfWeekWeatherIcon)
        }

        private fun setColor(position: Int, colors: IntArray) {
            itemView.setBackgroundColor(colors[position])
        }
    }
}