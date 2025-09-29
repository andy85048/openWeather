package com.andyliao.weathertest

import android.app.Application
import com.andyliao.weathertest.di.AppContainer

class WeatherApplication : Application() {

    lateinit var appContainer: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer(this)
    }
}