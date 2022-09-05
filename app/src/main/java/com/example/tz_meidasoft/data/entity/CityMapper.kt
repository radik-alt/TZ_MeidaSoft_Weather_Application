package com.example.tz_meidasoft.data.entity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tz_meidasoft.data.entity.dbModel.City
import com.example.tz_meidasoft.domain.entity.CityDomain
import javax.inject.Inject

class CityMapper @Inject constructor() {

    fun cityDomainToCity(cityDomain: CityDomain) = City(
        id = cityDomain.id,
        city = cityDomain.city,
        used = cityDomain.used
    )

    fun cityToCityDomain(city: City) = CityDomain(
        id = city.id,
        city = city.city,
        used = city.used
    )

    fun mapCityToListCityDomain(list: List<City>?):LiveData<List<CityDomain>>{
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