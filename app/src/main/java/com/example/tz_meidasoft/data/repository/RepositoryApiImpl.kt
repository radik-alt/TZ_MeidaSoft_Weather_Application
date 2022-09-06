package com.example.tz_meidasoft.data.repository

import com.example.tz_meidasoft.data.api.GetDataNetworkImpl
import com.example.tz_meidasoft.domain.entity.apiDomain.ApiDomain
import com.example.tz_meidasoft.domain.repository.RepositoryApi
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryApiImpl @Inject constructor() : RepositoryApi {

    override suspend fun getApiWeather(city: String): Response<ApiDomain> {
        return GetDataNetworkImpl.api.getApiWeather(city)
    }
}