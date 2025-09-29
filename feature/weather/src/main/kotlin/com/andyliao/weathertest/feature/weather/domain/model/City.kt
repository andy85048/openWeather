package com.andyliao.weathertest.feature.weather.domain.model

data class City(
    val id: Long,
    val name: String,
    val country: String,
    val latitude: Double,
    val longitude: Double
)