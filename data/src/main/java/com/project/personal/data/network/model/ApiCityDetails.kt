package com.project.personal.data.network.model

import com.google.gson.annotations.SerializedName

data class ApiCityDetails(

        @SerializedName("id")
        val cityId: Int,

        @SerializedName("title")
        val title: String,

        @SerializedName("sun_rise")
        val sunRise: String,

        @SerializedName("sun_set")
        val sunSet: String,

        @SerializedName("timezone_name")
        val timezoneName: String,

        @SerializedName("consolidated_weather")
        val consolidatedWeather: List<ConsolidatedWeather>
)