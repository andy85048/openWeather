package com.andyliao.weathertest.feature.cities.data.datasource.local

import com.andyliao.weathertest.core.database.dao.CityDao
import com.andyliao.weathertest.core.database.entity.CityEntity
import kotlinx.coroutines.flow.Flow

class CitiesLocalDataSource(
    private val cityDao: CityDao
) {
    fun getAllCities(): Flow<List<CityEntity>> {
        return cityDao.getAllCities()
    }

    suspend fun getSelectedCity(): CityEntity? {
        return cityDao.getSelectedCity()
    }

    fun getSelectedCityFlow(): Flow<CityEntity?> {
        return cityDao.getSelectedCityFlow()
    }

    suspend fun insertCities(cities: List<CityEntity>) {
        cityDao.insertCities(cities)
    }

    suspend fun selectCity(cityId: Long) {
        cityDao.clearAllSelections()
        cityDao.selectCity(cityId)
    }

    suspend fun clearAllCities() {
        cityDao.clearAllCities()
    }
}