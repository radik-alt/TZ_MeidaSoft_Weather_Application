package com.example.tz_meidasoft.presentation.сity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tz_meidasoft.data.mapper.CityMapper
import com.example.tz_meidasoft.data.repository.CityRepositoryImpl
import com.example.tz_meidasoft.data.room.DatabaseCity
import com.example.tz_meidasoft.domain.entity.CityDomain
import com.example.tz_meidasoft.domain.uescase.db.InsertCityUseCase
import com.example.tz_meidasoft.domain.uescase.db.UpdateCityUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddCityViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = DatabaseCity.getDatabaseCity(application).daoCity()
    private val mapper = CityMapper()
    private val repository = CityRepositoryImpl(dao, mapper)

    private val isEdit = MutableLiveData<Boolean>()
    private val city = MutableLiveData<CityDomain>()

    fun setEdit(edit: Boolean){
        isEdit.value = edit
    }

    fun getIsEdit():LiveData<Boolean> = isEdit

    fun setCity(cityDomain: CityDomain){
        city.value = cityDomain
    }

    fun getCity():LiveData<CityDomain> = city


    fun updateCity(city: CityDomain){
        UpdateCityUseCase(repository).updateCity(city)
    }

    fun insertCity(city:CityDomain){
        CoroutineScope(Dispatchers.Default).launch {
            InsertCityUseCase(repository).insertCity(city)
        }
    }
}