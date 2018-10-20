package com.project.personal.weatherapp.ui

import android.os.Bundle
import android.util.Log
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.project.personal.data.network.configuration.Urls
import com.project.personal.data.network.service.WeatherService
import com.project.personal.weatherapp.R
import com.project.personal.weatherapp.di.activity.ActivityComponent
import com.project.personal.weatherapp.di.activity.DaggerActivity
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.CoroutineExceptionHandler
import kotlinx.coroutines.experimental.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : DaggerActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(Urls.RETROFIT_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
                .build()

        val weatherService: WeatherService = retrofit.create(WeatherService::class.java)

        launch(CommonPool + CoroutineExceptionHandler { _, e ->
            Log.e("TAG", "CoroutineExceptionHandler", e)
        }) {
            Log.d("myData", weatherService.cityEntity("london").await().body()?.toString())
        }


    }

    override fun inject(activityComponent: ActivityComponent?) {
        activityComponent?.inject(this)
    }
}
