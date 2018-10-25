package com.project.personal.weatherapp.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

class ImageLoaderImpl(private val context: Context) : ImageLoader {


    override fun renderImage(imageSource: String, imageView: ImageView) {
        Glide.with(context).load(imageSource).into(imageView)
    }

}