package com.project.personal.data.network.model

import com.project.personal.domain.Failure
import com.project.personal.domain.Result
import com.project.personal.domain.Success
import com.project.personal.domain.mapper.Mappable
import com.project.personal.domain.model.CitySearchResult
import com.project.personal.domain.model.CitySearchResults

class ApiCities(private val apiCities: List<ApiCity>) : Mappable<Result<CitySearchResults>> {
    override fun mapToData(): Result<CitySearchResults> = when {
        isValid() -> {
            val list = ArrayList<CitySearchResult>()
            apiCities.forEach {
                if (it.isValid()) {
                    list.add(it.mapToData())
                }
            }
            Success(CitySearchResults(list))
        }
        else -> Failure(Exception("City body is invalid"))
    }

    override fun isValid(): Boolean {
        return true
    }
}