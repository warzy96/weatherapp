package com.project.personal.domain.interactor

import com.project.personal.domain.Result
import com.project.personal.domain.interactor.type.SingleQueryUseCase
import com.project.personal.domain.model.CitySearchResults
import com.project.personal.domain.repository.WeatherRepository
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class SearchCitiesUseCase @Inject
constructor(private val weatherRepository: WeatherRepository) :
        SingleQueryUseCase<String, Deferred<Result<CitySearchResults>>> {

    override suspend fun execute(param: String): Deferred<Result<CitySearchResults>> {
        return weatherRepository.citySearch(param)
    }
}