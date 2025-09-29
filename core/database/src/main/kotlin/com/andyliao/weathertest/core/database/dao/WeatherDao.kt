package com.andyliao.weathertest.core.database.dao

import androidx.room.*
import com.andyliao.weathertest.core.database.entity.CachedWeatherEntity

@Dao
interface WeatherDao {
    @Query("SELECT * FROM cached_weather WHERE id = :id")
    suspend fun getCachedWeather(id: String): CachedWeatherEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weather: CachedWeatherEntity)

    @Delete
    suspend fun deleteWeather(weather: CachedWeatherEntity)

    @Query("DELETE FROM cached_weather WHERE timestamp < :expiryTime")
    suspend fun deleteExpiredWeather(expiryTime: Long)

    @Query("DELETE FROM cached_weather")
    suspend fun clearAllWeather()
}