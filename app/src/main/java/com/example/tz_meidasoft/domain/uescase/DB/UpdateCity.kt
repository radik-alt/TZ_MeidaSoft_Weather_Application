package com.example.tz_meidasoft.domain.uescase.DB

import com.example.tz_meidasoft.domain.entity.CityDomain
import com.example.tz_meidasoft.domain.repository.CityRepository
import javax.inject.Inject

class UpdateCity @Inject constructor(private val repository: CityRepository) {

    fun updateCity(city:CityDomain){
        repository.updateCity(city)
    }
}