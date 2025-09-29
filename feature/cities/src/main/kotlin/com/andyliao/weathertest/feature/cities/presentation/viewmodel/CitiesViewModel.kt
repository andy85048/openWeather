package com.andyliao.weathertest.feature.cities.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andyliao.weathertest.feature.cities.domain.model.City
import com.andyliao.weathertest.feature.cities.domain.usecase.GetCitiesUseCase
import com.andyliao.weathertest.feature.cities.domain.usecase.SelectCityUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class CitiesUiState(
    val cities: List<City> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class CitiesViewModel(
    private val getCitiesUseCase: GetCitiesUseCase,
    private val selectCityUseCase: SelectCityUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CitiesUiState())
    val uiState: StateFlow<CitiesUiState> = _uiState.asStateFlow()

    init {
        loadCities()
    }

    private fun loadCities() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

            try {
                getCitiesUseCase().collect { cities ->
                    _uiState.value = _uiState.value.copy(
                        cities = cities,
                        isLoading = false,
                        errorMessage = null
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Unknown error occurred"
                )
            }
        }
    }

    fun selectCity(city: City) {
        viewModelScope.launch {
            try {
                selectCityUseCase(city.id)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    errorMessage = e.message ?: "Failed to select city"
                )
            }
        }
    }
}