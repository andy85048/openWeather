package com.andyliao.weathertest.feature.weather.domain.usecase

import com.andyliao.weathertest.core.common.Resource
import com.andyliao.weathertest.feature.weather.domain.model.City
import com.andyliao.weathertest.feature.weather.domain.model.DailyWeather
import com.andyliao.weathertest.feature.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow

class GetWeeklyForecastUseCase(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(city: City): Flow<Resource<List<DailyWeather>>> {
        return repository.getWeeklyForecast(city.latitude, city.longitude)
    }
}