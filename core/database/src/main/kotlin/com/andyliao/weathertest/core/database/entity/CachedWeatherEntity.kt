package com.andyliao.weathertest.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.andyliao.weathertest.core.common.Constants

@Entity(tableName = Constants.WEATHER_TABLE_NAME)
data class CachedWeatherEntity(
    @PrimaryKey
    val id: String, // lat_lon combination
    val latitude: Double,
    val longitude: Double,
    val temperature: Double,
    val feelsLike: Double,
    val humidity: Int,
    val description: String,
    val icon: String,
    val windSpeed: Double,
    val pressure: Double,
    val visibility: Double,
    val timestamp: Long
)