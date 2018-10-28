package com.project.personal.weatherapp.util

import com.project.personal.data.network.configuration.Urls

const val IMAGE_FORMAT: String = ".png"
fun parseImageUrl(weatherStateAbbr: String): String {
    return Urls.ICONS_BASE_URL + weatherStateAbbr + IMAGE_FORMAT
}