package com.example.tz_meidasoft.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tz_meidasoft.data.mapper.CityMapper
import com.example.tz_meidasoft.data.entity.dbModel.CityEntity
import com.example.tz_meidasoft.data.room.DaoCity
import com.example.tz_meidasoft.domain.entity.CityDomain
import com.example.tz_meidasoft.domain.repository.CityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityRepositoryImpl @Inject constructor(
    private val daoCity: DaoCity,
    private val cityMapper: CityMapper
): CityRepository {

    override suspend fun insetCity(city: CityDomain) {
        daoCity.insetCity(cityMapper.cityDomainToCity(city))
    }

    override suspend fun getAllCityFlow():Flow<List<CityEntity>>{
        return daoCity.getAllCityFlow()
    }

    override fun deleteCity(id: Long) {
        daoCity.deleteCity(id)
    }

    override fun updateCity(city: CityDomain) {
        daoCity.updateCity(cityMapper.cityDomainToCity(city))
    }

    override fun getByUsedCity(): LiveData<CityDomain> {
        val liveDataCityDomain : MutableLiveData<CityDomain> = MutableLiveData()
        liveDataCityDomain.value = cityMapper.cityToCityDomain(daoCity.getByUsedCity())
        return liveDataCityDomain
    }

    override fun setOtherCityNotUsed() {
        daoCity.setOtherCityNotUsed()
    }

}