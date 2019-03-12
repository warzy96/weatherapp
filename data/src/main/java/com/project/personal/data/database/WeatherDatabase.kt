package com.project.personal.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.project.personal.data.database.dao.CitiesDao
import com.project.personal.data.database.model.DbCity

@Database(entities = [DbCity::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun citiesDao(): CitiesDao
}