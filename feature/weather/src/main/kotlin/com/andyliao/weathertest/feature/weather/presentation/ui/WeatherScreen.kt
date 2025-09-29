package com.andyliao.weathertest.feature.weather.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andyliao.weathertest.core.common.DateUtils
import com.andyliao.weathertest.core.ui.components.ErrorMessage
import com.andyliao.weathertest.core.ui.components.LoadingIndicator
import com.andyliao.weathertest.core.ui.components.WeatherCard
import com.andyliao.weathertest.feature.weather.presentation.viewmodel.WeatherViewModel
import com.andyliao.weathertest.feature.weather.domain.model.City
import com.andyliao.weathertest.feature.weather.domain.model.Weather
import com.andyliao.weathertest.feature.weather.domain.model.DailyWeather

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(
    onCityClick: () -> Unit,
    viewModel: WeatherViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                CitySelector(
                    selectedCity = uiState.selectedCity,
                    onClick = onCityClick
                )
            }

            item {
                Text(
                    text = "當日天氣預報",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 0.dp)
                )
            }

            item {
                when {
                    uiState.isLoading && uiState.currentWeather == null -> {
                        LoadingIndicator(message = "Loading weather data...")
                    }
                    uiState.errorMessage != null && uiState.currentWeather == null -> {
                        ErrorMessage(
                            message = uiState.errorMessage ?: "Unknown error",
                            onRetry = { viewModel.onRefresh() }
                        )
                    }
                    uiState.currentWeather != null -> {
                        CurrentWeatherCard(weather = uiState.currentWeather!!)
                    }
                }
            }

            if (uiState.weeklyForecast.isNotEmpty()) {
                item {
                    Text(
                        text = "一週天氣預報",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }

                items(uiState.weeklyForecast) { dailyWeather ->
                    WeeklyForecastItem(dailyWeather = dailyWeather)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CitySelector(
    selectedCity: City,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Location",
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "${selectedCity.name}, ${selectedCity.country}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun CurrentWeatherCard(
    weather: Weather
) {
    WeatherCard(
        temperature = "${weather.temperature.toInt()}°C",
        description = weather.description,
        feelsLike = "${weather.feelsLike.toInt()}°C",
        humidity = "${weather.humidity}%",
        windSpeed = "${weather.windSpeed} m/s",
        icon = "https://openweathermap.org/img/wn/${weather.icon}@2x.png",
        pressure = "${weather.pressure.toInt()} hPa"
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WeeklyForecastItem(
    dailyWeather: DailyWeather
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "${DateUtils.formatDate(dailyWeather.date)}號",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        modifier = Modifier.size(30.dp),
                        model = "https://openweathermap.org/img/wn/${dailyWeather.icon}@2x.png",
                        contentScale = ContentScale.Fit,
                        contentDescription = "Weather icon"
                    )
                    Text(
                        text = dailyWeather.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${dailyWeather.tempMax.toInt()}°C",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = " / ${dailyWeather.tempMin.toInt()}°C",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}


// Preview Mock Data
private val sampleCity = City(
    id = 1,
    name = "Taipei",
    country = "Taiwan",
    latitude = 25.0330,
    longitude = 121.5654
)

private val sampleWeather = Weather(
    temperature = 25.5,
    feelsLike = 27.0,
    humidity = 65,
    description = "Partly cloudy",
    icon = "02d",
    windSpeed = 3.5,
    pressure = 1013.0,
    visibility = 10.0
)

private val sampleDailyWeather = listOf(
    DailyWeather(
        date = System.currentTimeMillis(),
        temperature = 24.0,
        tempMin = 20.0,
        tempMax = 28.0,
        description = "Sunny",
        icon = "01d",
        humidity = 60,
        windSpeed = 2.5
    ),
    DailyWeather(
        date = System.currentTimeMillis() + 86400000,
        temperature = 22.0,
        tempMin = 18.0,
        tempMax = 26.0,
        description = "Cloudy",
        icon = "03d",
        humidity = 70,
        windSpeed = 4.0
    ),
    DailyWeather(
        date = System.currentTimeMillis() + 172800000,
        temperature = 20.0,
        tempMin = 16.0,
        tempMax = 24.0,
        description = "Light rain",
        icon = "10d",
        humidity = 80,
        windSpeed = 3.0
    )
)

// Preview Functions
@Preview(showBackground = true)
@Composable
fun CitySelectorPreview() {
    MaterialTheme {
        CitySelector(
            selectedCity = sampleCity,
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CurrentWeatherCardPreview() {
    MaterialTheme {
        CurrentWeatherCard(weather = sampleWeather)
    }
}

@Preview(showBackground = true)
@Composable
fun WeeklyForecastItemPreview() {
    MaterialTheme {
        WeeklyForecastItem(dailyWeather = sampleDailyWeather[0])
    }
}