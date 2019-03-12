package com.project.personal.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.project.personal.domain.mapper.Mappable
import com.project.personal.domain.model.City

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

) : Mappable<City> {
    override fun mapToData(): City = when {
        isValid() -> City(title, woeid)
        else -> City(title, woeid)
    }

    override fun isValid(): Boolean {
        return true
    }
}