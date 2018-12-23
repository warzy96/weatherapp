package com.project.personal.domain.interactor

import com.project.personal.domain.Result
import com.project.personal.domain.interactor.type.SingleQueryMultipleParamsUseCase
import com.project.personal.domain.model.CitySearchResults
import com.project.personal.domain.repository.WeatherRepository
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class SearchCitiesByCoordinatesUseCase
@Inject
constructor(private val weatherRepository: WeatherRepository) : SingleQueryMultipleParamsUseCase<Double, Deferred<Result<CitySearchResults>>> {

    override suspend fun execute(param1: Double, param2: Double): Deferred<Result<CitySearchResults>> {
        return weatherRepository.citySearch(param1, param2)
    }
}