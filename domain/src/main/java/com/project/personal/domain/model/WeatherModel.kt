package com.project.personal.domain.model

class WeatherModel(
        val title: String,
        val sunRise: String,
        val sunSet: String,
        val timezoneName: String,
        val weatherDetailsList: List<WeatherDetailsModel>
)