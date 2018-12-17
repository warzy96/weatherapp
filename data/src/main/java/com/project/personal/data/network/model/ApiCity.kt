package com.project.personal.data.network.model

import com.google.gson.annotations.SerializedName
import com.project.personal.domain.mapper.Mappable
import com.project.personal.domain.model.CitySearchResult

data class ApiCity(

        @SerializedName("title")
        val title: String,

        @SerializedName("location_type")
        val locationType: String,

        @SerializedName("woeid")
        val woeid: Int

) : Mappable<CitySearchResult> {
    override fun mapToData(): CitySearchResult {
        return CitySearchResult(title, locationType, woeid)
    }

    override fun isValid(): Boolean {
        return true
    }
}