package com.example.tz_meidasoft.domain.uescase.db

import com.example.tz_meidasoft.domain.repository.CityRepository
import javax.inject.Inject

class DeleteCityUseCase @Inject constructor (private val cityRepository: CityRepository) {

    fun deleteCity(id:Long){
        cityRepository.deleteCity(id)
    }
}