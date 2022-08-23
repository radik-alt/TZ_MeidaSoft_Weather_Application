package com.example.tz_meidasoft.domain.repository

import androidx.lifecycle.LiveData
import com.example.tz_meidasoft.data.entity.apiModel.ApiModel
import com.example.tz_meidasoft.domain.entity.apiDomain.ApiDomain
import retrofit2.Response

interface RepositoryApi {

    suspend fun getApiWeather(city:String):Response<ApiDomain>

}