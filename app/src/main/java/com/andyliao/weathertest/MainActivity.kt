package com.andyliao.weathertest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.andyliao.weathertest.ui.theme.WeatherTestTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize default cities through AppContainer
        val appContainer = (application as WeatherApplication).appContainer
        lifecycleScope.launch {
            val selectedCity = appContainer.citiesRepository.getSelectedCity()
            if (selectedCity == null) {
                appContainer.citiesRepository.initializeDefaultCities()
            }
        }

        setContent {
            WeatherTestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WeatherApp()
                }
            }
        }
    }
}