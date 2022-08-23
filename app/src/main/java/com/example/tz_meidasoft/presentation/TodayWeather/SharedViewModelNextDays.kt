package com.example.tz_meidasoft.presentation.TodayWeather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tz_meidasoft.data.entity.apiModel.ApiModel
import com.example.tz_meidasoft.domain.entity.apiDomain.ApiDomain

class SharedViewModelNextDays : ViewModel() {

    private val responseCity = MutableLiveData<ApiDomain>()

    fun setResponse(apiModel: ApiDomain){
        responseCity.value = apiModel
    }

    fun getResponse():LiveData<ApiDomain>{
        return responseCity
    }
}