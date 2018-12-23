package com.project.personal.data.network.service

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.project.personal.data.network.model.ApiCities
import com.project.personal.data.network.model.ApiCity
import java.lang.reflect.Type

class SearchResultDeserializer : JsonDeserializer<ApiCities> {

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): ApiCities {
        val array = json?.asJsonArray
        val results = ArrayList<ApiCity>()
        for (i in 0 until array!!.size()) {
            val city = array.get(i).asJsonObject
            val title = city.get("title").asString
            val locationType = city.get("location_type").asString
            val woeid = city.get("woeid").asInt
            results.add(ApiCity(title, locationType, woeid))
        }
        return ApiCities(results)
    }
}