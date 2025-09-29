package com.andyliao.weathertest

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.andyliao.weathertest.feature.cities.presentation.ui.CitiesScreen
import com.andyliao.weathertest.feature.cities.presentation.viewmodel.CitiesViewModel
import com.andyliao.weathertest.feature.weather.presentation.ui.WeatherScreen
import com.andyliao.weathertest.feature.weather.presentation.viewmodel.WeatherViewModel

@Composable
fun WeatherApp() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val appContainer = (context.applicationContext as WeatherApplication).appContainer

    NavHost(
        navController = navController,
        startDestination = "weather"
    ) {
        composable("weather") {
            val weatherViewModel = WeatherViewModel(
                getCurrentWeatherUseCase = appContainer.getCurrentWeatherUseCase,
                getWeeklyForecastUseCase = appContainer.getWeeklyForecastUseCase,
                getSelectedCityUseCase = appContainer.getSelectedCityUseCase
            )
            WeatherScreen(
                onCityClick = { navController.navigate("cities") },
                viewModel = weatherViewModel
            )
        }

        composable("cities") {
            val citiesViewModel = CitiesViewModel(
                getCitiesUseCase = appContainer.getCitiesUseCase,
                selectCityUseCase = appContainer.selectCityUseCase
            )
            CitiesScreen(
                onCitySelected = { _ ->
                    navController.popBackStack()
                },
                onBackPressed = { navController.popBackStack() },
                viewModel = citiesViewModel
            )
        }
    }
}