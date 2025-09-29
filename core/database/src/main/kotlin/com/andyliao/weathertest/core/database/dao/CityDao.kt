package com.andyliao.weathertest.core.database.dao

import androidx.room.*
import com.andyliao.weathertest.core.database.entity.CityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {
    @Query("SELECT * FROM cities ORDER BY name ASC")
    fun getAllCities(): Flow<List<CityEntity>>

    @Query("SELECT * FROM cities WHERE isSelected = 1 LIMIT 1")
    suspend fun getSelectedCity(): CityEntity?

    @Query("SELECT * FROM cities WHERE isSelected = 1 LIMIT 1")
    fun getSelectedCityFlow(): Flow<CityEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCities(cities: List<CityEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: CityEntity)

    @Query("UPDATE cities SET isSelected = 0")
    suspend fun clearAllSelections()

    @Query("UPDATE cities SET isSelected = 1 WHERE id = :cityId")
    suspend fun selectCity(cityId: Long)

    @Query("DELETE FROM cities")
    suspend fun clearAllCities()
}