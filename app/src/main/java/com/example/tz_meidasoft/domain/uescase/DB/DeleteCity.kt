package com.example.tz_meidasoft.domain.uescase.DB

import com.example.tz_meidasoft.domain.repository.CityRepository
import javax.inject.Inject

class DeleteCity @Inject constructor (private val cityRepository: CityRepository) {

    fun deleteCity(id:Long){
        cityRepository.deleteCity(id)
    }
}