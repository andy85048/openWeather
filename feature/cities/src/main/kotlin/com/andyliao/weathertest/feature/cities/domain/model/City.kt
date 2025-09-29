package com.andyliao.weathertest.feature.cities.domain.model

data class City(
    val id: Long,
    val name: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
    val isSelected: Boolean = false
)