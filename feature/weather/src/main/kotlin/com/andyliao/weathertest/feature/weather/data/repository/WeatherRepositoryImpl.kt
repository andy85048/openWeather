package com.andyliao.weathertest.feature.weather.data.repository

import com.andyliao.weathertest.core.common.Resource
import com.andyliao.weathertest.core.network.BuildConfig
import com.andyliao.weathertest.feature.weather.data.datasource.local.WeatherLocalDataSource
import com.andyliao.weathertest.feature.weather.data.datasource.remote.WeatherRemoteDataSource
import com.andyliao.weathertest.feature.weather.data.mapper.toCachedWeatherEntity
import com.andyliao.weathertest.feature.weather.data.mapper.toDailyWeatherList
import com.andyliao.weathertest.feature.weather.data.mapper.toWeather
import com.andyliao.weathertest.feature.weather.domain.model.DailyWeather
import com.andyliao.weathertest.feature.weather.domain.model.Weather
import com.andyliao.weathertest.feature.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WeatherRepositoryImpl(
    private val remoteDataSource: WeatherRemoteDataSource,
    private val localDataSource: WeatherLocalDataSource
) : WeatherRepository {

    override suspend fun getCurrentWeather(lat: Double, lon: Double): Flow<Resource<Weather>> = flow {
        emit(Resource.Loading())

        try {
            val cachedWeather = localDataSource.getCachedWeather(lat, lon)
            if (cachedWeather != null && !localDataSource.isExpired(cachedWeather)) {
                emit(Resource.Success(cachedWeather.toWeather()))
            }

            val remoteWeather = remoteDataSource.getCurrentWeather(lat, lon, BuildConfig.WEATHER_API_KEY)
            val weather = remoteWeather.toWeather()
            localDataSource.cacheWeather(weather.toCachedWeatherEntity(lat, lon))
            emit(Resource.Success(weather))

        } catch (e: Exception) {
            val cachedWeather = localDataSource.getCachedWeather(lat, lon)
            if (cachedWeather != null) {
                emit(Resource.Success(cachedWeather.toWeather()))
            } else {
                val errorMessage = when {
                    e.message?.contains("401") == true -> "API Key無效，請設置正確的OpenWeatherMap API Key"
                    e.message?.contains("network") == true -> "網路連接失敗，請檢查網路設置"
                    e.message?.contains("timeout") == true -> "請求超時，請稍後重試"
                    else -> "獲取天氣資料失敗：${e.message ?: "未知錯誤"}"
                }
                emit(Resource.Error(errorMessage))
            }
        }
    }

    override suspend fun getWeeklyForecast(lat: Double, lon: Double): Flow<Resource<List<DailyWeather>>> = flow {
        emit(Resource.Loading())

        try {
            val forecast = remoteDataSource.getWeatherForecast(lat, lon, BuildConfig.WEATHER_API_KEY)
            emit(Resource.Success(forecast.toDailyWeatherList()))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }
}