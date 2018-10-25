package com.project.personal.domain.interactor

import com.project.personal.domain.interactor.type.SingleQueryUseCase
import com.project.personal.domain.model.CitySearchResult
import com.project.personal.domain.repository.WeatherRepository
import kotlinx.coroutines.experimental.Deferred
import javax.inject.Inject

class SearchCitiesUseCase @Inject
constructor(private val weatherRepository: WeatherRepository) :
        SingleQueryUseCase<String, Deferred<List<CitySearchResult>>> {

    override suspend fun execute(param: String): Deferred<List<CitySearchResult>> {
        return weatherRepository.citySearch(param)
    }
}