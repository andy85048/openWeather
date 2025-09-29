package com.andyliao.weathertest.feature.cities.data.repository

import com.andyliao.weathertest.core.common.Constants
import com.andyliao.weathertest.core.database.entity.CityEntity
import com.andyliao.weathertest.feature.cities.data.datasource.local.CitiesLocalDataSource
import com.andyliao.weathertest.feature.cities.data.mapper.toCity
import com.andyliao.weathertest.feature.cities.data.mapper.toCityList
import com.andyliao.weathertest.feature.cities.domain.model.City
import com.andyliao.weathertest.feature.cities.domain.repository.CitiesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CitiesRepositoryImpl(
    private val localDataSource: CitiesLocalDataSource
) : CitiesRepository {

    override fun getAllCities(): Flow<List<City>> {
        return localDataSource.getAllCities().map { entities ->
            entities.toCityList()
        }
    }

    override suspend fun getSelectedCity(): City? {
        return localDataSource.getSelectedCity()?.toCity()
    }

    override fun getSelectedCityFlow(): Flow<City?> {
        return localDataSource.getSelectedCityFlow().map { entity ->
            entity?.toCity()
        }
    }

    override suspend fun selectCity(cityId: Long) {
        localDataSource.selectCity(cityId)
    }

    override suspend fun initializeDefaultCities() {
        val defaultCities = listOf(
            CityEntity(
                id = Constants.DEFAULT_CITY_ID,
                name = Constants.DEFAULT_CITY_NAME,
                country = Constants.DEFAULT_CITY_COUNTRY,
                latitude = Constants.DEFAULT_CITY_LAT,
                longitude = Constants.DEFAULT_CITY_LON,
                isSelected = true
            ),
            CityEntity(
                id = 2L,
                name = "Tokyo",
                country = "JP",
                latitude = 35.6762,
                longitude = 139.6503,
                isSelected = false
            ),
            CityEntity(
                id = 3L,
                name = "New York",
                country = "US",
                latitude = 40.7128,
                longitude = -74.0060,
                isSelected = false
            ),
            CityEntity(
                id = 4L,
                name = "London",
                country = "GB",
                latitude = 51.5074,
                longitude = -0.1278,
                isSelected = false
            ),
            CityEntity(
                id = 5L,
                name = "Sydney",
                country = "AU",
                latitude = -33.8688,
                longitude = 151.2093,
                isSelected = false
            ),
            CityEntity(
                id = 6L,
                name = "Paris",
                country = "FR",
                latitude = 48.8566,
                longitude = 2.3522,
                isSelected = false
            )
        )
        localDataSource.insertCities(defaultCities)
    }
}