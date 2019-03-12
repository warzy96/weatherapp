package com.project.personal.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.project.personal.data.database.model.DbCity

@Dao
interface CitiesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCityAsync(city: DbCity)

    @Query("SELECT * FROM city")
    fun fetchAllCitiesAsync(): List<DbCity>
}