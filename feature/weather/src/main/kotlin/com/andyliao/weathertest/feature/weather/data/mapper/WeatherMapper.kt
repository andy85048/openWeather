package com.andyliao.weathertest.feature.weather.data.mapper

import com.andyliao.weathertest.core.database.entity.CachedWeatherEntity
import com.andyliao.weathertest.core.network.dto.CurrentWeatherDto
import com.andyliao.weathertest.core.network.dto.ForecastDto
import com.andyliao.weathertest.core.network.dto.ForecastItemDto
import com.andyliao.weathertest.feature.weather.domain.model.DailyWeather
import com.andyliao.weathertest.feature.weather.domain.model.Weather
import com.andyliao.weathertest.core.common.DateUtils

fun CurrentWeatherDto.toWeather(): Weather {
    return Weather(
        temperature = main.temp,
        feelsLike = main.feelsLike,
        humidity = main.humidity,
        description = weather.firstOrNull()?.description?.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase() else it.toString()
        } ?: "",
        icon = weather.firstOrNull()?.icon ?: "",
        windSpeed = wind.speed,
        pressure = main.pressure,
        visibility = visibility.toDouble()
    )
}

fun Weather.toCachedWeatherEntity(lat: Double, lon: Double): CachedWeatherEntity {
    return CachedWeatherEntity(
        id = "${lat}_${lon}",
        latitude = lat,
        longitude = lon,
        temperature = temperature,
        feelsLike = feelsLike,
        humidity = humidity,
        description = description,
        icon = icon,
        windSpeed = windSpeed,
        pressure = pressure,
        visibility = visibility,
        timestamp = DateUtils.getCurrentTime()
    )
}

fun CachedWeatherEntity.toWeather(): Weather {
    return Weather(
        temperature = temperature,
        feelsLike = feelsLike,
        humidity = humidity,
        description = description,
        icon = icon,
        windSpeed = windSpeed,
        pressure = pressure,
        visibility = visibility
    )
}

fun ForecastDto.toDailyWeatherList(): List<DailyWeather> {
    return list.groupBy { item ->
        DateUtils.formatDate(item.dt)
    }.map { (_, items) ->
        val firstItem = items.first()
        val tempList = items.map { it.main.temp }
        DailyWeather(
            date = firstItem.dt,
            temperature = tempList.average(),
            tempMin = tempList.minOrNull() ?: 0.0,
            tempMax = tempList.maxOrNull() ?: 0.0,
            description = firstItem.weather.firstOrNull()?.description?.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase() else it.toString()
            } ?: "",
            icon = firstItem.weather.firstOrNull()?.icon ?: "",
            humidity = firstItem.main.humidity,
            windSpeed = firstItem.wind.speed
        )
    }.take(7) // Take only 7 days
}