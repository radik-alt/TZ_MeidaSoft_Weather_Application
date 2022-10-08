package com.example.tz_meidasoft.data.mapper

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tz_meidasoft.data.entity.dbModel.CityEntity
import com.example.tz_meidasoft.domain.entity.CityDomain
import javax.inject.Inject

class CityMapper @Inject constructor() {

    fun cityDomainToCity(cityDomain: CityDomain) = CityEntity(
        id = cityDomain.id,
        city = cityDomain.city,
        used = cityDomain.used
    )

    fun cityToCityDomain(cityEntity: CityEntity) = CityDomain(
        id = cityEntity.id,
        city = cityEntity.city,
        used = cityEntity.used
    )

    fun mapCityToListCityDomain(list: List<CityEntity>?):LiveData<List<CityDomain>>{
        val listTemp = ArrayList<CityDomain>()
        if (list != null){
            for (i in list){
                listTemp.add(cityToCityDomain(i))
            }
        }
        val liveDataCityDomain : MutableLiveData<List<CityDomain>> = MutableLiveData()
        liveDataCityDomain.value = listTemp
        return liveDataCityDomain
    }


}