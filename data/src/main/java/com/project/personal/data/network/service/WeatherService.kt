package com.project.personal.data.network.service

import com.project.personal.data.network.model.ApiCity
import com.project.personal.data.network.model.ApiCityDetails
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherService {

    @GET("location/search/")
    fun cityEntity(@Query("query") cityName: String): List<ApiCity>

    @GET("location/{cityId}")
    fun cityDetailsEntity(@Path("cityId") cityId: Int): ApiCityDetails
}