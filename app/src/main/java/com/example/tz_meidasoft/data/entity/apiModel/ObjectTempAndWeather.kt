package com.example.tz_meidasoft.data.entity.apiModel

import com.example.tz_meidasoft.domain.entity.apiDomain.Temp
import com.example.tz_meidasoft.domain.entity.apiDomain.Weather

data class ObjectTempAndWeather(
    val day:String,
    val temp: Temp,
    val weather: Weather,
)