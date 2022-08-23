package com.example.tz_meidasoft.domain.uescase.DB

import com.example.tz_meidasoft.domain.entity.CityDomain
import com.example.tz_meidasoft.domain.repository.CityRepository

class InsertCity(private val repository: CityRepository) {

    suspend fun insertCity(city:CityDomain){
        repository.insetCity(city)
    }
}