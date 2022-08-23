package com.example.tz_meidasoft.domain.entity.apiDomain

data class ApiDomain(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<ListApi>,
    val message: Double
)