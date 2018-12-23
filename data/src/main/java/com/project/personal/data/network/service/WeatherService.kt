package com.project.personal.data.network.service

import com.project.personal.data.Location
import com.project.personal.data.network.model.ApiCities
import com.project.personal.data.network.model.ApiCityDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherService {

    @GET("location/search/")
    fun citySearch(@Query("query") cityName: String): Call<ApiCities>

    @GET("location/search/")
    fun citySearch(@Query("lattlong") location: Location): Call<ApiCities>

    @GET("location/{cityId}")
    fun cityDetailsEntity(@Path("cityId") cityId: Int): Call<ApiCityDetails>
}