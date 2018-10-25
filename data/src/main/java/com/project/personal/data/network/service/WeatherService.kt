package com.project.personal.data.network.service

import com.project.personal.data.network.model.ApiCity
import com.project.personal.data.network.model.ApiCityDetails
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherService {

    @GET("location/search/")
    fun citySearch(@Query("query") cityName: String): Deferred<List<ApiCity>>

    @GET("location/{cityId}")
    fun cityDetailsEntity(@Path("cityId") cityId: Int): Deferred<ApiCityDetails>
}