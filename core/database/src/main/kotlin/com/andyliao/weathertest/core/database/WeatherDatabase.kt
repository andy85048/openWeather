package com.andyliao.weathertest.core.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.andyliao.weathertest.core.database.dao.CityDao
import com.andyliao.weathertest.core.database.dao.WeatherDao
import com.andyliao.weathertest.core.database.entity.CachedWeatherEntity
import com.andyliao.weathertest.core.database.entity.CityEntity
import com.andyliao.weathertest.core.common.Constants

@Database(
    entities = [CachedWeatherEntity::class, CityEntity::class],
    version = 1,
    exportSchema = false
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
    abstract fun cityDao(): CityDao

    companion object {
        fun create(context: Context): WeatherDatabase {
            return Room.databaseBuilder(
                context,
                WeatherDatabase::class.java,
                Constants.DATABASE_NAME
            ).build()
        }
    }
}