package com.andyliao.weathertest.feature.weather.data.datasource.local

import com.andyliao.weathertest.core.database.dao.WeatherDao
import com.andyliao.weathertest.core.database.entity.CachedWeatherEntity
import com.andyliao.weathertest.core.common.DateUtils

class WeatherLocalDataSource(
    private val weatherDao: WeatherDao
) {
    suspend fun getCachedWeather(lat: Double, lon: Double): CachedWeatherEntity? {
        val id = "${lat}_${lon}"
        return weatherDao.getCachedWeather(id)
    }

    suspend fun cacheWeather(weather: CachedWeatherEntity) {
        weatherDao.insertWeather(weather)
    }

    suspend fun clearExpiredCache() {
        val expiryTime = DateUtils.getCurrentTime() - com.andyliao.weathertest.core.common.Constants.CACHE_EXPIRY_TIME
        weatherDao.deleteExpiredWeather(expiryTime)
    }

    fun isExpired(weather: CachedWeatherEntity): Boolean {
        return DateUtils.isExpired(weather.timestamp)
    }
}