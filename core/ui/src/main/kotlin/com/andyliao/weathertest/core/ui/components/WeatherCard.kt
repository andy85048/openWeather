package com.andyliao.weathertest.core.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherCard(
    temperature: String,
    description: String,
    feelsLike: String,
    humidity: String,
    windSpeed: String,
    pressure: String,
    icon: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = temperature,
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 16.dp),
            ) {
                AsyncImage(
                    modifier = Modifier.size(42.dp),
                    model = icon,
                    contentScale = ContentScale.Fit,
                    contentDescription = "Weather icon"
                )
                Text(
                    text = description,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                WeatherInfoItem("體感溫度", feelsLike)
                WeatherInfoItem("濕度", humidity)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                WeatherInfoItem("風速", windSpeed)
                WeatherInfoItem("大氣壓力", pressure)
            }
        }
    }
}

@Composable
private fun WeatherInfoItem(
    label: String,
    value: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherCardPreview() {
    MaterialTheme {
        WeatherCard(
            temperature = "25°C",
            description = "Partly cloudy",
            feelsLike = "27°C",
            humidity = "65%",
            windSpeed = "3.5 m/s",
            icon = "https://openweathermap.org/img/wn/10d@2x.png",
            pressure = "1013 hPa",
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherInfoItemPreview() {
    MaterialTheme {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            WeatherInfoItem("Humidity", "65%")
            WeatherInfoItem("Wind", "3.5 m/s")
        }
    }
}