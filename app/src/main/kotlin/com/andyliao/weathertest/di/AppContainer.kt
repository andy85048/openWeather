package com.andyliao.weathertest.di

import android.content.Context
import androidx.room.Room
import com.andyliao.weathertest.core.common.NetworkUtils
import com.andyliao.weathertest.core.database.WeatherDatabase
import com.andyliao.weathertest.core.network.ApiKeyInterceptor
import com.andyliao.weathertest.core.network.BuildConfig
import com.andyliao.weathertest.core.network.NetworkConnectionInterceptor
import com.andyliao.weathertest.core.network.WeatherApiService
import com.andyliao.weathertest.feature.cities.data.datasource.local.CitiesLocalDataSource
import com.andyliao.weathertest.feature.cities.data.repository.CitiesRepositoryImpl
import com.andyliao.weathertest.feature.cities.domain.repository.CitiesRepository
import com.andyliao.weathertest.feature.cities.domain.usecase.GetCitiesUseCase
import com.andyliao.weathertest.feature.cities.domain.usecase.GetSelectedCityUseCase
import com.andyliao.weathertest.feature.cities.domain.usecase.SelectCityUseCase
import com.andyliao.weathertest.feature.weather.data.datasource.local.WeatherLocalDataSource
import com.andyliao.weathertest.feature.weather.data.datasource.remote.WeatherRemoteDataSource
import com.andyliao.weathertest.feature.weather.data.repository.WeatherRepositoryImpl
import com.andyliao.weathertest.feature.weather.domain.repository.WeatherRepository
import com.andyliao.weathertest.feature.weather.domain.usecase.GetCurrentWeatherUseCase
import com.andyliao.weathertest.feature.weather.domain.usecase.GetWeeklyForecastUseCase
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppContainer(private val context: Context) {

    // Network utilities
    private val networkUtils by lazy { NetworkUtils(context) }

    // Database
    private val database by lazy {
        Room.databaseBuilder(
            context,
            WeatherDatabase::class.java,
            "weather_database"
        ).build()
    }

    // Interceptors
    private val apiKeyInterceptor by lazy { ApiKeyInterceptor() }
    private val networkConnectionInterceptor by lazy { NetworkConnectionInterceptor(networkUtils) }

    // OkHttp client
    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(networkConnectionInterceptor)
            .addInterceptor(apiKeyInterceptor)
            .build()
    }

    // Retrofit
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // API Service
    private val weatherApiService by lazy { retrofit.create(WeatherApiService::class.java) }

    // Data Sources
    private val weatherRemoteDataSource by lazy { WeatherRemoteDataSource(weatherApiService) }
    private val weatherLocalDataSource by lazy { WeatherLocalDataSource(database.weatherDao()) }
    private val citiesLocalDataSource by lazy { CitiesLocalDataSource(database.cityDao()) }

    // Repositories
    val weatherRepository: WeatherRepository by lazy {
        WeatherRepositoryImpl(weatherRemoteDataSource, weatherLocalDataSource)
    }

    val citiesRepository: CitiesRepository by lazy {
        CitiesRepositoryImpl(citiesLocalDataSource)
    }

    // Use Cases
    val getCurrentWeatherUseCase by lazy { GetCurrentWeatherUseCase(weatherRepository) }
    val getWeeklyForecastUseCase by lazy { GetWeeklyForecastUseCase(weatherRepository) }
    val getCitiesUseCase by lazy { GetCitiesUseCase(citiesRepository) }
    val getSelectedCityUseCase by lazy { GetSelectedCityUseCase(citiesRepository) }
    val selectCityUseCase by lazy { SelectCityUseCase(citiesRepository) }
}