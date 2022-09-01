package com.example.tz_meidasoft.DI

import androidx.lifecycle.ViewModel
import com.example.tz_meidasoft.presentation.TodayWeather.SharedViewModelNextDays
import com.example.tz_meidasoft.presentation.TodayWeather.WeatherViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(WeatherViewModel::class)
    @Binds
    fun bindWeatherViewModel(impl: WeatherViewModel) : ViewModel

}