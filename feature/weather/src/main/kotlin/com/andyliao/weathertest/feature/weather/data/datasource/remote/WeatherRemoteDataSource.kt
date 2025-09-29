package com.andyliao.weathertest.feature.weather.data.datasource.remote

import com.andyliao.weathertest.core.network.WeatherApiService
import com.andyliao.weathertest.core.network.dto.CurrentWeatherDto
import com.andyliao.weathertest.core.network.dto.ForecastDto

class WeatherRemoteDataSource(
    private val apiService: WeatherApiService
) {
    suspend fun getCurrentWeather(
        lat: Double,
        lon: Double,
        apiKey: String
    ): CurrentWeatherDto {
        return apiService.getCurrentWeather(lat, lon, apiKey)
    }

    suspend fun getWeatherForecast(
        lat: Double,
        lon: Double,
        apiKey: String
    ): ForecastDto {
        return apiService.getWeatherForecast(lat, lon, apiKey)
    }
}