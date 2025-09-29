package com.andyliao.weathertest.feature.weather.domain.model

data class Weather(
    val temperature: Double,
    val feelsLike: Double,
    val humidity: Int,
    val description: String,
    val icon: String,
    val windSpeed: Double,
    val pressure: Double,
    val visibility: Double
)