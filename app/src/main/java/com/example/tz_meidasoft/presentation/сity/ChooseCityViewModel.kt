package com.example.tz_meidasoft.presentation.сity

import android.app.Application
import android.content.Context
import android.view.View
import androidx.lifecycle.*
import com.example.tz_meidasoft.data.entity.dbModel.CityEntity
import com.example.tz_meidasoft.data.mapper.CityMapper
import com.example.tz_meidasoft.data.repository.CityRepositoryImpl
import com.example.tz_meidasoft.data.room.DatabaseCity
import com.example.tz_meidasoft.domain.entity.CityDomain
import com.example.tz_meidasoft.domain.uescase.db.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChooseCityViewModel @Inject constructor(
    private val getListCityUseCase: GetListCityUseCase,
    private val deleteCityUseCase: DeleteCityUseCase,
    private val updateCityUseCase: UpdateCityUseCase,
    private val insertCityUseCase: InsertCityUseCase,
    private val setCityNotUsed: SetCityNotUsed
) : ViewModel() {


    private val responseDb = MutableLiveData<List<CityDomain>>()

    fun getDataList():LiveData<List<CityDomain>>{
        getFlowDataList()
        return responseDb
    }

    private fun getFlowDataList(){
        viewModelScope.launch {
            getListCityUseCase.getListCity().collect{
                val mapper = CityMapper()
                val post = mapper.mapCityToListCityDomain(it)
                responseDb.postValue(post.value)
            }
        }
    }

    fun deleteCity(id:Long){
        deleteCityUseCase.deleteCity(id)
    }

    fun updateCity(city:CityDomain){
        updateCityUseCase.updateCity(city)
    }

    fun setOtherCityNotUsed(){
        setCityNotUsed.setCityNotUsed()
    }

    private fun insertCity(city:CityDomain){
        CoroutineScope(Dispatchers.Main).launch {
            insertCityUseCase.insertCity(city)
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