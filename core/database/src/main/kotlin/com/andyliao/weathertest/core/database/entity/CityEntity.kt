package com.andyliao.weathertest.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.andyliao.weathertest.core.common.Constants

@Entity(tableName = Constants.CITIES_TABLE_NAME)
data class CityEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
    val isSelected: Boolean = false
)