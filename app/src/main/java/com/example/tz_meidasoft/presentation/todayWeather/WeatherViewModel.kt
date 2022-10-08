package com.example.tz_meidasoft.presentation.todayWeather

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.*
import com.example.tz_meidasoft.data.repository.CityRepositoryImpl
import com.example.tz_meidasoft.data.repository.RepositoryApiImpl
import com.example.tz_meidasoft.domain.entity.CityDomain
import com.example.tz_meidasoft.domain.entity.apiDomain.ApiDomain
import com.example.tz_meidasoft.domain.uescase.api.CityApiUseCase
import com.example.tz_meidasoft.domain.uescase.db.GetUsedCityUseCase
import kotlinx.coroutines.*
import retrofit2.Response
import javax.inject.Inject


class WeatherViewModel @Inject constructor(
    private val getUsedCityUseCase: GetUsedCityUseCase,
    private val cityApiUseCase: CityApiUseCase
) : ViewModel() {

    val responseApi: MutableLiveData<Response<ApiDomain>> = MutableLiveData()

    fun getUsedCity():LiveData<CityDomain>{
        return getUsedCityUseCase.getByUsedCity()
    }

    fun getWeatherCity(city:String){
        viewModelScope.launch {
            responseApi.value = cityApiUseCase.getApiDataCityName(city)
        }
    }

    fun toast(context: Context, error: String){
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }


}