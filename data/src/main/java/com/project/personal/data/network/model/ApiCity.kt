package com.project.personal.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.project.personal.domain.mapper.Mappable
import com.project.personal.domain.model.CitySearchResult

data class ApiCity(

        @SerializedName("title")
        @Expose
        val title: String,

        @SerializedName("location_type")
        @Expose
        val locationType: String,

        @SerializedName("woeid")
        @Expose
        val woeid: Int

) : Mappable<CitySearchResult> {
    override fun mapToData(): CitySearchResult = when {
        isValid() -> CitySearchResult(title, locationType, woeid)
        else -> CitySearchResult(title, locationType, woeid)
    }

    override fun isValid(): Boolean {
        return true
    }
}