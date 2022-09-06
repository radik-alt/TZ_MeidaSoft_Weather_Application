package com.example.tz_meidasoft.presentation.City

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tz_meidasoft.data.entity.CityMapper
import com.example.tz_meidasoft.data.entity.dbModel.City
import com.example.tz_meidasoft.data.repository.CityRepositoryImpl
import com.example.tz_meidasoft.data.room.DatabaseCity
import com.example.tz_meidasoft.domain.entity.CityDomain
import com.example.tz_meidasoft.domain.uescase.DB.DeleteCity
import com.example.tz_meidasoft.domain.uescase.DB.GetListCity
import com.example.tz_meidasoft.domain.uescase.DB.InsertCity
import com.example.tz_meidasoft.domain.uescase.DB.UpdateCity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChooseCityViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = DatabaseCity.getDatabaseCity(application).daoCity()
    private val mapper = CityMapper()
    private val repository = CityRepositoryImpl(dao, mapper)

    private val responseDb = MutableLiveData<List<CityDomain>>()

    fun getDataList():LiveData<List<CityDomain>>{
        getFlowDataList()
        return responseDb
    }

    private fun getFlowDataList(){
        viewModelScope.launch {
            GetListCity(repository).getListCity().collect{
                val post = mapper.mapCityToListCityDomain(it)
                responseDb.postValue(post.value)
            }
        }
    }

    fun deleteCity(id:Long){
        DeleteCity(repository).deleteCity(id)
    }

    fun updateCity(city:CityDomain){
        UpdateCity(repository).updateCity(city)
    }

    fun setOtherCityNotUsed(){
        repository.setOtherCityNotUsed()
    }

    fun insertCity(city:CityDomain){
        CoroutineScope(Dispatchers.Main).launch {
            InsertCity(repository).insertCity(city)
        }
    }
}