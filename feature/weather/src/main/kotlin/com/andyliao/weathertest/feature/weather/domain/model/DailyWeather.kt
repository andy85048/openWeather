package com.andyliao.weathertest.feature.weather.domain.model

data class DailyWeather(
    val date: Long,
    val temperature: Double,
    val tempMin: Double,
    val tempMax: Double,
    val description: String,
    val icon: String,
    val humidity: Int,
    val windSpeed: Double,
)