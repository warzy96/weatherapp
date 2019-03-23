package com.project.personal.weatherapp.ui.summary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.personal.weatherapp.R
import com.project.personal.weatherapp.util.ImageLoader
import com.project.personal.weatherapp.util.parseImageUrl
import kotlinx.android.synthetic.main.city_summary_layout.view.*
import javax.inject.Inject
import kotlin.math.roundToInt

class SummaryAdapter
@Inject
constructor(val layoutInflater: LayoutInflater,
            val imageLoader: ImageLoader) : RecyclerView.Adapter<SummaryAdapter
.CardViewHolder>() {

    private var cityCards = ArrayList<CitySummaryViewModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(layoutInflater.inflate(R.layout.city_summary_layout, parent, false), imageLoader)
    }

    override fun getItemCount(): Int {
        return cityCards.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.render(cityCards[position])
    }

    fun setCities(citySummaryListViewModel: CitySummaryListViewModel) {
        cityCards.clear()
        cityCards.addAll(citySummaryListViewModel.cities)
        notifyDataSetChanged()
    }

    class CardViewHolder(itemView: View, val imageLoader: ImageLoader) : RecyclerView.ViewHolder
    (itemView) {

        fun render(citySummaryViewModel: CitySummaryViewModel) {
            itemView.cityNameTextView.text = citySummaryViewModel.cityName
            itemView.temperatureTextView.text = citySummaryViewModel.cityCurrentTemperature
                    .roundToInt().toString()
            imageLoader.renderImage(parseImageUrl(citySummaryViewModel.weatherStateAbbr),
                    itemView.forecastImageView)
        }
    }
}