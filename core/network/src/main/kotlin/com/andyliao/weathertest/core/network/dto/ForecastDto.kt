package com.andyliao.weathertest.core.network.dto

import com.google.gson.annotations.SerializedName

data class ForecastDto(
    @SerializedName("cod")
    val cod: String,
    @SerializedName("message")
    val message: Int,
    @SerializedName("cnt")
    val cnt: Int,
    @SerializedName("list")
    val list: List<ForecastItemDto>,
    @SerializedName("city")
    val city: CityDto
)

data class ForecastItemDto(
    @SerializedName("dt")
    val dt: Long,
    @SerializedName("main")
    val main: MainDto,
    @SerializedName("weather")
    val weather: List<WeatherItemDto>,
    @SerializedName("clouds")
    val clouds: CloudsDto,
    @SerializedName("wind")
    val wind: WindDto,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("pop")
    val pop: Double,
    @SerializedName("sys")
    val sys: ForecastSysDto,
    @SerializedName("dt_txt")
    val dtTxt: String
)

data class CityDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("coord")
    val coord: CoordDto,
    @SerializedName("country")
    val country: String,
    @SerializedName("population")
    val population: Int,
    @SerializedName("timezone")
    val timezone: Int,
    @SerializedName("sunrise")
    val sunrise: Long,
    @SerializedName("sunset")
    val sunset: Long
)

data class CoordDto(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double
)

data class CloudsDto(
    @SerializedName("all")
    val all: Int
)

data class ForecastSysDto(
    @SerializedName("pod")
    val pod: String
)