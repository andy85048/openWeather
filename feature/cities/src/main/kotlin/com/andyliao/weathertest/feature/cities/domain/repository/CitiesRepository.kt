package com.andyliao.weathertest.feature.cities.domain.repository

import com.andyliao.weathertest.feature.cities.domain.model.City
import kotlinx.coroutines.flow.Flow

interface CitiesRepository {
    fun getAllCities(): Flow<List<City>>
    suspend fun getSelectedCity(): City?
    fun getSelectedCityFlow(): Flow<City?>
    suspend fun selectCity(cityId: Long)
    suspend fun initializeDefaultCities()
}