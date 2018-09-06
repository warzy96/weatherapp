package com.project.personal.domain.model

class WeatherDetailsModel(
        val id: Int,
        val applicableDate: String,
        val weatherState: String,
        val weatherStateAbbr: String,
        val windSpeed: Float,
        val windDirection: String,
        val minTemp: Int,
        val maxTemp: Int,
        val currentTemp: Int,
        val airPressure: Float,
        val humidity: Float,
        val visibility: Float,
        val predictability: Int
)