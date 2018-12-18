package com.project.personal.domain.interactor

import com.project.personal.domain.Result
import com.project.personal.domain.interactor.type.SingleQueryUseCase
import com.project.personal.domain.model.WeatherModel
import com.project.personal.domain.repository.WeatherRepository
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class FetchFiveDayForecastUseCase @Inject
constructor(private val weatherRepository: WeatherRepository) :
        SingleQueryUseCase<Int, Deferred<Result<WeatherModel>>> {

    override suspend fun execute(param: Int): Deferred<Result<WeatherModel>> {
        return weatherRepository.fiveDayForecast(param)
    }
}