package com.project.personal.weatherapp.di.application.module

import android.content.Context
import com.project.personal.weatherapp.di.application.ForApplication
import com.project.personal.weatherapp.util.ImageLoader
import com.project.personal.weatherapp.util.ImageLoaderImpl
import com.project.personal.weatherapp.util.StringUtil
import com.project.personal.weatherapp.util.StringUtilImpl
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
    fun provideStringUtils(@ForApplication context: Context): StringUtil {
        return StringUtilImpl(context)
    }
}