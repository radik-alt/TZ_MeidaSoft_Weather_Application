package com.example.tz_meidasoft.domain.uescase.db

import androidx.lifecycle.LiveData
import com.example.tz_meidasoft.domain.entity.CityDomain
import com.example.tz_meidasoft.domain.repository.CityRepository
import javax.inject.Inject

class GetUsedCityUseCase @Inject constructor(private val repository: CityRepository) {

    fun getByUsedCity():LiveData<CityDomain>{
        return repository.getByUsedCity()
    }
}