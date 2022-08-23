package com.example.tz_meidasoft.domain.entity.apiDomain

data class City(
    val coord: Coord,
    val country: String,
    val id: Int,
    val name: String,
    val population: Int,
    val timezone: Int
)