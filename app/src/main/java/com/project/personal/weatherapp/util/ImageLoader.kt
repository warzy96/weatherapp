package com.project.personal.weatherapp.util

import android.widget.ImageView

interface ImageLoader {

    fun renderImage(imageSource: String, imageView: ImageView)
}