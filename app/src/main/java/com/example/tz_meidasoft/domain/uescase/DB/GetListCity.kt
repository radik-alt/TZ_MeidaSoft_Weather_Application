package com.example.tz_meidasoft.domain.uescase.DB

import androidx.lifecycle.LiveData
import com.example.tz_meidasoft.data.entity.dbModel.City
import com.example.tz_meidasoft.data.room.DaoCity
import com.example.tz_meidasoft.domain.entity.CityDomain
import com.example.tz_meidasoft.domain.repository.CityRepository

class GetListCity(
    private val cityRepository: CityRepository
) {

    fun getListCity() : LiveData<List<CityDomain>> {
        return cityRepository.getAllCity()
    }
}