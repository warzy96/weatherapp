package com.project.personal.domain.model

class WeatherModel(
        private val title: String,
        private val sunRise: String,
        private val sunSet: String,
        private val timezoneName: String,
        private val weatherDetailsList: List<WeatherDetailsModel>
)