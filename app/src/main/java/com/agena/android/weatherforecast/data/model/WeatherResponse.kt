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
    @SerializedName("name")
    val cityName: String,
)
