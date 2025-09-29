package com.andyliao.weathertest.feature.cities.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.andyliao.weathertest.core.ui.components.ErrorMessage
import com.andyliao.weathertest.core.ui.components.LoadingIndicator
import com.andyliao.weathertest.feature.cities.domain.model.City
import com.andyliao.weathertest.feature.cities.presentation.viewmodel.CitiesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CitiesScreen(
    onCitySelected: (City) -> Unit,
    onBackPressed: () -> Unit,
    viewModel: CitiesViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TopAppBar(
            title = { Text("Select City") },
            navigationIcon = {
                IconButton(onClick = onBackPressed) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Back"
                    )
                }
            }
        )

        when {
            uiState.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    LoadingIndicator(message = "Loading cities...")
                }
            }
            uiState.errorMessage != null -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    ErrorMessage(message = uiState.errorMessage ?: "Unknown error")
                }
            }
            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(uiState.cities) { city ->
                        CityItem(
                            city = city,
                            onCityClick = { selectedCity ->
                                viewModel.selectCity(selectedCity)
                                onCitySelected(selectedCity)
                            }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CityItem(
    city: City,
    onCityClick: (City) -> Unit
) {
    Card(
        onClick = { onCityClick(city) },
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = city.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = city.country,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            if (city.isSelected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Selected",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

// Preview Data
private val sampleCities = listOf(
    City(
        id = 1,
        name = "Taipei",
        country = "Taiwan",
        latitude = 25.0330,
        longitude = 121.5654,
        isSelected = true
    ),
    City(
        id = 2,
        name = "Tokyo",
        country = "Japan",
        latitude = 35.6762,
        longitude = 139.6503,
        isSelected = false
    ),
    City(
        id = 3,
        name = "New York",
        country = "United States",
        latitude = 40.7128,
        longitude = -74.0060,
        isSelected = false
    ),
    City(
        id = 4,
        name = "London",
        country = "United Kingdom",
        latitude = 51.5074,
        longitude = -0.1278,
        isSelected = false
    ),
    City(
        id = 5,
        name = "Paris",
        country = "France",
        latitude = 48.8566,
        longitude = 2.3522,
        isSelected = false
    )
)

// Preview Functions
@Preview(showBackground = true)
@Composable
fun CityItemSelectedPreview() {
    MaterialTheme {
        CityItem(
            city = sampleCities[0], // Selected city
            onCityClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CityItemUnselectedPreview() {
    MaterialTheme {
        CityItem(
            city = sampleCities[1], // Unselected city
            onCityClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CityListPreview() {
    MaterialTheme {
        LazyColumn(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(sampleCities.take(3)) { city ->
                CityItem(
                    city = city,
                    onCityClick = {}
                )
            }
        }
    }
}