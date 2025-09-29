package com.andyliao.weathertest.feature.weather.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andyliao.weathertest.core.common.Resource
import com.andyliao.weathertest.core.common.Constants
import com.andyliao.weathertest.feature.weather.domain.model.City
import com.andyliao.weathertest.feature.weather.domain.model.DailyWeather
import com.andyliao.weathertest.feature.weather.domain.model.Weather
import com.andyliao.weathertest.feature.weather.domain.usecase.GetCurrentWeatherUseCase
import com.andyliao.weathertest.feature.weather.domain.usecase.GetWeeklyForecastUseCase
import com.andyliao.weathertest.feature.cities.domain.usecase.GetSelectedCityUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class WeatherUiState(
    val selectedCity: City = City(
        id = Constants.DEFAULT_CITY_ID,
        name = Constants.DEFAULT_CITY_NAME,
        country = Constants.DEFAULT_CITY_COUNTRY,
        latitude = Constants.DEFAULT_CITY_LAT,
        longitude = Constants.DEFAULT_CITY_LON
    ),
    val currentWeather: Weather? = null,
    val weeklyForecast: List<DailyWeather> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class WeatherViewModel(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getWeeklyForecastUseCase: GetWeeklyForecastUseCase,
    private val getSelectedCityUseCase: GetSelectedCityUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    init {
        observeSelectedCity()
        loadWeatherData()
    }

    private fun observeSelectedCity() {
        viewModelScope.launch {
            getSelectedCityUseCase().collect { citiesCity: com.andyliao.weathertest.feature.cities.domain.model.City? ->
                citiesCity?.let { city: com.andyliao.weathertest.feature.cities.domain.model.City ->
                    val weatherCity = City(
                        id = city.id,
                        name = city.name,
                        country = city.country,
                        latitude = city.latitude,
                        longitude = city.longitude
                    )
                    _uiState.value = _uiState.value.copy(selectedCity = weatherCity)
                    loadWeatherData()
                }
            }
        }
    }

    fun onCitySelected(city: City) {
        _uiState.value = _uiState.value.copy(selectedCity = city)
        loadWeatherData()
    }

    fun onRefresh() {
        loadWeatherData()
    }

    private fun loadWeatherData() {
        val currentCity = _uiState.value.selectedCity
        loadCurrentWeather(currentCity)
        loadWeeklyForecast(currentCity)
    }

    private fun loadCurrentWeather(city: City) {
        viewModelScope.launch {
            getCurrentWeatherUseCase(city).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = true,
                            errorMessage = null
                        )
                    }
                    is Resource.Success -> {
                        _uiState.value = _uiState.value.copy(
                            currentWeather = resource.data,
                            isLoading = false,
                            errorMessage = null
                        )
                    }
                    is Resource.Error -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            errorMessage = resource.message
                        )
                    }
                }
            }
        }
    }

    private fun loadWeeklyForecast(city: City) {
        viewModelScope.launch {
            getWeeklyForecastUseCase(city).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        // Loading state is already handled by current weather
                    }
                    is Resource.Success -> {
                        _uiState.value = _uiState.value.copy(
                            weeklyForecast = resource.data
                        )
                    }
                    is Resource.Error -> {
                        // Error state is already handled by current weather
                    }
                }
            }
        }
    }
}