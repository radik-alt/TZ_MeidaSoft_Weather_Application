package com.example.tz_meidasoft.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.tz_meidasoft.data.entity.dbModel.City
import retrofit2.http.DELETE

@Dao
interface DaoCity {

    @Query("SELECT * FROM city")
    fun getAllCity() : List<City>

    @Insert
    suspend fun insetCity(city: City)

    @Query("DELETE FROM city WHERE id=:idCity")
    fun deleteCity(idCity:Long)

    @Update
    fun updateCity(city: City)

    @Query("SELECT * FROM City WHERE used == 1")
    fun getByUsedCity(): City

    @Query("Update City SET used = 0 WHERE used == 1")
    fun setOtherCityNotUsed()
}