package com.example.tz_meidasoft.domain.uescase.DB

import com.example.tz_meidasoft.domain.entity.CityDomain
import com.example.tz_meidasoft.domain.repository.CityRepository
import javax.inject.Inject

class InsertCity @Inject constructor(private val repository: CityRepository) {

    suspend fun insertCity(city:CityDomain){
        repository.insetCity(city)
    }
}