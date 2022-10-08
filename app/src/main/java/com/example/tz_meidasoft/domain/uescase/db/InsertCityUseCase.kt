package com.example.tz_meidasoft.domain.uescase.db

import com.example.tz_meidasoft.domain.entity.CityDomain
import com.example.tz_meidasoft.domain.repository.CityRepository
import javax.inject.Inject

class InsertCityUseCase @Inject constructor(private val repository: CityRepository) {

    suspend fun insertCity(city:CityDomain){
        repository.insetCity(city)
    }
}