package com.andyliao.weathertest.feature.cities.domain.usecase

import com.andyliao.weathertest.feature.cities.domain.model.City
import com.andyliao.weathertest.feature.cities.domain.repository.CitiesRepository
import kotlinx.coroutines.flow.Flow

class GetCitiesUseCase(
    private val repository: CitiesRepository
) {
    operator fun invoke(): Flow<List<City>> {
        return repository.getAllCities()
    }
}