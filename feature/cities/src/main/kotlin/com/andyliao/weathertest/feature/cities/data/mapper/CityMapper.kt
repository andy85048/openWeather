package com.andyliao.weathertest.feature.cities.data.mapper

import com.andyliao.weathertest.core.database.entity.CityEntity
import com.andyliao.weathertest.feature.cities.domain.model.City

fun CityEntity.toCity(): City {
    return City(
        id = id,
        name = name,
        country = country,
        latitude = latitude,
        longitude = longitude,
        isSelected = isSelected
    )
}

fun City.toCityEntity(): CityEntity {
    return CityEntity(
        id = id,
        name = name,
        country = country,
        latitude = latitude,
        longitude = longitude,
        isSelected = isSelected
    )
}

fun List<CityEntity>.toCityList(): List<City> {
    return map { it.toCity() }
}