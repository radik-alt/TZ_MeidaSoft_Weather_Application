package com.example.tz_meidasoft.domain.uescase.db

import com.example.tz_meidasoft.data.entity.dbModel.CityEntity
import com.example.tz_meidasoft.domain.repository.CityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetListCityUseCase @Inject constructor(
    private val cityRepository: CityRepository
) {

    suspend fun getListCity() : Flow<List<CityEntity>> {
        return cityRepository.getAllCityFlow()
    }
}