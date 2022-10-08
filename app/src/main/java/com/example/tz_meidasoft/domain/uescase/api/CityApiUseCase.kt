package com.example.tz_meidasoft.domain.uescase.api

import com.example.tz_meidasoft.domain.entity.apiDomain.ApiDomain
import com.example.tz_meidasoft.domain.repository.RepositoryApi
import retrofit2.Response
import javax.inject.Inject

class CityApiUseCase @Inject constructor (private val repositoryApi: RepositoryApi) {

    suspend fun getApiDataCityName(city:String): Response<ApiDomain> {
        return repositoryApi.getApiWeather(city)
    }
}