package com.andyliao.weathertest.core.common

object Constants {
    const val CACHE_EXPIRY_TIME = 5 * 60 * 1000L // 5 minutes in milliseconds
    const val DATABASE_NAME = "weather_database"
    const val WEATHER_TABLE_NAME = "cached_weather"
    const val CITIES_TABLE_NAME = "cities"

    const val DEFAULT_CITY_ID = 1L
    const val DEFAULT_CITY_NAME = "Taipei"
    const val DEFAULT_CITY_COUNTRY = "TW"
    const val DEFAULT_CITY_LAT = 25.0330
    const val DEFAULT_CITY_LON = 121.5654
}