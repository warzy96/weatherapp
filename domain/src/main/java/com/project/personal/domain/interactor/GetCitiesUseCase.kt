package com.project.personal.domain.interactor

import com.project.personal.domain.interactor.type.FetchUseCase
import com.project.personal.domain.model.City
import com.project.personal.domain.repository.WeatherRepository
import javax.inject.Inject

class GetCitiesUseCase
@Inject
constructor(val weatherRepository: WeatherRepository) : FetchUseCase<List<City>> {

    override fun execute(): List<City> {
        return weatherRepository.fetchCities()
    }
}