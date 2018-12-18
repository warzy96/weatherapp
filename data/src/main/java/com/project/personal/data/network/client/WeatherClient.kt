package com.project.personal.data.network.client

import com.project.personal.domain.Failure
import com.project.personal.domain.Result
import com.project.personal.domain.Success
import com.project.personal.data.network.mappers.WeatherMapper
import com.project.personal.data.network.service.WeatherService
import com.project.personal.domain.mapper.Mappable
import com.project.personal.domain.model.CitySearchResults
import com.project.personal.domain.model.WeatherModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class WeatherClient(private val weatherService: WeatherService) {

    suspend fun getCitySearchResults(cityName: String): Deferred<Result<CitySearchResults>> = GlobalScope.async {
        val result = weatherService.citySearch(cityName).getResult()

        when (result) {
            is Success -> result.data.mapToData()
            is Failure -> result
        }
    }

    suspend fun getCityDetails(cityId: Int): Deferred<Result<WeatherModel>> = GlobalScope.async {
        val result = weatherService.cityDetailsEntity(cityId).getResult()
        when (result) {
            is Success -> result.data.mapToData()
            is Failure -> result
        }
    }

    suspend fun <T : Mappable<R>, R : Any> Call<T>.getResult(): Result<T> = suspendCoroutine {
        enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>?, error: Throwable?) {
                it.resume(Failure(error))
            }

            override fun onResponse(call: Call<T>?, response: Response<T>?) {
                response?.body()?.run { it.resume(Success(this)) }
                response?.errorBody()?.run { it.resume(Failure(HttpException(response))) }
            }
        })
    }
}