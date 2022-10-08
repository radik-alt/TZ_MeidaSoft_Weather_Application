package com.example.tz_meidasoft.presentation.сity

import android.app.Application
import android.content.Context
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tz_meidasoft.data.mapper.CityMapper
import com.example.tz_meidasoft.data.repository.CityRepositoryImpl
import com.example.tz_meidasoft.data.room.DatabaseCity
import com.example.tz_meidasoft.domain.entity.CityDomain
import com.example.tz_meidasoft.domain.uescase.db.DeleteCityUseCase
import com.example.tz_meidasoft.domain.uescase.db.GetListCityUseCase
import com.example.tz_meidasoft.domain.uescase.db.InsertCityUseCase
import com.example.tz_meidasoft.domain.uescase.db.UpdateCityUseCase
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
            GetListCityUseCase(repository).getListCity().collect{
                val post = mapper.mapCityToListCityDomain(it)
                responseDb.postValue(post.value)
            }
        }
    }

    fun deleteCity(id:Long){
        DeleteCityUseCase(repository).deleteCity(id)
    }

    fun updateCity(city:CityDomain){
        UpdateCityUseCase(repository).updateCity(city)
    }

    fun setOtherCityNotUsed(){
        repository.setOtherCityNotUsed()
    }

    private fun insertCity(city:CityDomain){
        CoroutineScope(Dispatchers.Main).launch {
            InsertCityUseCase(repository).insertCity(city)
        }
    }

    fun getItemCity(index: Int): CityDomain? {
        return responseDb.value?.get(index)
    }

    fun showSnackBar(view: View, error: String, city: CityDomain){
        Snackbar.make(view, error, Snackbar.LENGTH_SHORT)
            .setAction("Отмена", View.OnClickListener {
                insertCity(city)
            }).show()
    }
}