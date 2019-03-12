package com.project.personal.domain.interactor

import com.project.personal.domain.interactor.type.CompletableUseCase
import com.project.personal.domain.model.City
import com.project.personal.domain.repository.WeatherRepository
import javax.inject.Inject

class InsertCityUseCase
@Inject
constructor(private val weatherRepository: WeatherRepository) : CompletableUseCase<City> {

    override fun execute(param: City) {
        weatherRepository.insertCity(param)
    }
}