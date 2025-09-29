package com.andyliao.weathertest.feature.cities.domain.usecase

import com.andyliao.weathertest.feature.cities.domain.repository.CitiesRepository

class SelectCityUseCase(
    private val repository: CitiesRepository
) {
    suspend operator fun invoke(cityId: Long) {
        repository.selectCity(cityId)
    }
}