package com.example.tz_meidasoft.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tz_meidasoft.data.entity.CityMapper
import com.example.tz_meidasoft.data.entity.dbModel.City
import com.example.tz_meidasoft.data.room.DaoCity
import com.example.tz_meidasoft.domain.entity.CityDomain
import com.example.tz_meidasoft.domain.repository.CityRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Singleton
class CityRepositoryImpl @Inject constructor(
    private val daoCity: DaoCity,
    private val cityMapper: CityMapper
): CityRepository {

    override suspend fun insetCity(city: CityDomain) {
        daoCity.insetCity(cityMapper.cityDomainToCity(city))
    }

    override suspend fun getAllCityFlow():Flow<List<City>>{
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