package com.andyliao.weathertest.feature.weather.domain.repository

import com.andyliao.weathertest.core.common.Resource
import com.andyliao.weathertest.feature.weather.domain.model.DailyWeather
import com.andyliao.weathertest.feature.weather.domain.model.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getCurrentWeather(lat: Double, lon: Double): Flow<Resource<Weather>>
    suspend fun getWeeklyForecast(lat: Double, lon: Double): Flow<Resource<List<DailyWeather>>>
}