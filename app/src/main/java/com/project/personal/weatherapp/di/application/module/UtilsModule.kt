package com.project.personal.weatherapp.di.application.module

import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.location.LocationManager
import com.project.personal.weatherapp.di.application.ForApplication
import com.project.personal.weatherapp.util.ImageLoader
import com.project.personal.weatherapp.util.ImageLoaderImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UtilsModule {

    @Provides
    @Singleton
    fun provideImageLoader(@ForApplication context: Context): ImageLoader {
        return ImageLoaderImpl(context)
    }

    @Provides
    @Singleton
    fun provideLocationManager(@ForApplication context: Context): LocationManager {
        return context.getSystemService(LOCATION_SERVICE) as LocationManager
    }
}