package com.project.personal.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city")
class DbCity(

        @PrimaryKey
        var woeid: Int,

        var cityName: String
)