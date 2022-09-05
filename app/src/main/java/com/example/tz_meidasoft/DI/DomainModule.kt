package com.example.tz_meidasoft.DI

import com.example.tz_meidasoft.data.repository.CityRepositoryImpl
import com.example.tz_meidasoft.data.repository.RepositoryApiImpl
import com.example.tz_meidasoft.domain.repository.CityRepository
import com.example.tz_meidasoft.domain.repository.RepositoryApi
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DomainModule {

    @Singleton
    @Binds
    fun bindCityRepository(impl: CityRepositoryImpl):CityRepository

    @Singleton
    @Binds
    fun bindRepositoryApi(impl: RepositoryApiImpl):RepositoryApi
}