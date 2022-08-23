package com.example.tz_meidasoft.domain.uescase.DB

import com.example.tz_meidasoft.domain.repository.CityRepository

class DeleteCity(private val cityRepository: CityRepository) {

    fun deleteCity(id:Long){
        cityRepository.deleteCity(id)
    }
}