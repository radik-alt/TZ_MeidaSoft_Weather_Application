package com.example.tz_meidasoft.domain.uescase.DB

import androidx.lifecycle.LiveData
import com.example.tz_meidasoft.domain.entity.CityDomain
import com.example.tz_meidasoft.domain.repository.CityRepository

class GetByUsedCity(private val repository: CityRepository) {

    fun getByUsedCity():LiveData<CityDomain>{
        return repository.getByUsedCity()
    }
}