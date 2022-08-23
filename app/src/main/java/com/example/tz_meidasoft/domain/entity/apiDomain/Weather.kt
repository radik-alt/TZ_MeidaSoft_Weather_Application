package com.example.tz_meidasoft.domain.entity.apiDomain

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)