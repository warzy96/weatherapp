package com.project.personal.data.network.model

import com.google.gson.annotations.SerializedName
import com.project.personal.domain.Failure
import com.project.personal.domain.Result
import com.project.personal.domain.Success
import com.project.personal.domain.mapper.Mappable
import com.project.personal.domain.model.WeatherDetailsModel
import com.project.personal.domain.model.WeatherModel
import java.lang.Exception
import java.util.*

data class ApiCityDetails(

        @SerializedName("consolidated_weather")
        val consolidatedWeather: List<ConsolidatedWeather>,

        @SerializedName("woeid")
        val cityId: Int,

        @SerializedName("title")
        val title: String,

        @SerializedName("sun_rise")
        val sunRise: String,

        @SerializedName("sun_set")
        val sunSet: String,

        @SerializedName("timezone")
        val timezoneName: String

) : Mappable<Result<WeatherModel>> {

    override fun mapToData(): Result<WeatherModel> = when {
        isValid() -> {
            val weatherDetailsModelList: ArrayList<WeatherDetailsModel> = ArrayList()
            consolidatedWeather.forEach {
                if (it.isValid()) {
                    weatherDetailsModelList.add(it.mapToData())
                }
            }
            Success(WeatherModel(title, sunRise, sunSet, timezoneName, weatherDetailsModelList))
        }
        else -> Failure(Exception("Weather details body is invalid"))
    }

    override fun isValid(): Boolean {
        return true
    }

}