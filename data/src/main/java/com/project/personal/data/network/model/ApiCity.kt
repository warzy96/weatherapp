package com.project.personal.data.network.model

import com.google.gson.annotations.SerializedName

data class ApiCity(

        @SerializedName("title")
        val title: String,

        @SerializedName("location_type")
        val locationType: String,

        @SerializedName("woeid")
        val woeid: Int
)