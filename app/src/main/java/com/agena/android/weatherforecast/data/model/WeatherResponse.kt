package com.agena.android.weatherforecast.data.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("coord")
    val coord: Coordinate,
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("main")
    val temperature: Temperature,
    @SerializedName("wind")
    val wind: Wind,
    @SerializedName("sys")
    val system: System,
    @SerializedName("id")
    val cityId: Int,
    @SerializedName("name")
    val cityName: String,
    @SerializedName("timezone")
    val timezone: Int
)
