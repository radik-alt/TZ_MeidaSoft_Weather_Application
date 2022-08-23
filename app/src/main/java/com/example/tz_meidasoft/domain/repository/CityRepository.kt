package com.example.tz_meidasoft.domain.repository

import androidx.lifecycle.LiveData
import com.example.tz_meidasoft.data.entity.dbModel.City
import com.example.tz_meidasoft.domain.entity.CityDomain

interface CityRepository {

    suspend fun insetCity(city : CityDomain)

    fun getAllCity():LiveData<List<CityDomain>>

    fun deleteCity(id:Long)

    fun updateCity(city: CityDomain)

    fun getByUsedCity():LiveData<CityDomain>

    fun setOtherCityNotUsed()
}