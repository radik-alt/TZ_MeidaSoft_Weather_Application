package com.example.tz_meidasoft.di

import androidx.lifecycle.ViewModel
import com.example.tz_meidasoft.presentation.todayWeather.WeatherViewModel
import com.example.tz_meidasoft.presentation.сity.ChooseCityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(WeatherViewModel::class)
    @Binds
    fun bindWeatherViewModel(impl: WeatherViewModel) : ViewModel

//    @IntoMap
//    @ViewModelKey(ChooseCityViewModel::class)
//    @Binds
//    fun bindChooseCityViewModel(impl: ChooseCityViewModel) : ViewModel


}