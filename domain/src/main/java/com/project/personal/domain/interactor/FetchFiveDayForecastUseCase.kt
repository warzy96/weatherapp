package com.project.personal.domain.interactor

import com.project.personal.domain.interactor.type.SingleQueryUseCase
import com.project.personal.domain.model.WeatherModel
import com.project.personal.domain.repository.WeatherRepository
import kotlinx.coroutines.experimental.Deferred
import javax.inject.Inject

class FetchFiveDayForecastUseCase @Inject
constructor(private val weatherRepository: WeatherRepository) :
        SingleQueryUseCase<Int, Deferred<WeatherModel>> {

    override suspend fun execute(param: Int): Deferred<WeatherModel> {
        return weatherRepository.fiveDayForecast(param)
    }
}