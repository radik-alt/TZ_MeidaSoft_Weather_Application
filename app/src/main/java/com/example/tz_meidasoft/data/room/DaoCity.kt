package com.example.tz_meidasoft.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.tz_meidasoft.data.entity.dbModel.CityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoCity {

    @Query("SELECT * FROM cityentity")
    fun getAllCity() : List<CityEntity>

    @Query("SELECT * FROM cityentity")
    fun getAllCityFlow():Flow<List<CityEntity>>

    @Insert
    suspend fun insetCity(cityEntity: CityEntity)

    @Query("DELETE FROM cityentity WHERE id=:idCity")
    fun deleteCity(idCity:Long)

    @Update
    fun updateCity(cityEntity: CityEntity)

    @Query("SELECT * FROM CityEntity WHERE used == 1")
    fun getByUsedCity(): CityEntity

    @Query("Update CityEntity SET used = 0 WHERE used == 1")
    fun setOtherCityNotUsed()
}