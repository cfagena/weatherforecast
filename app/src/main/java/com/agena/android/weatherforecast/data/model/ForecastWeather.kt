package com.agena.android.weatherforecast.data.model

import com.google.gson.annotations.SerializedName

data class ForecastWeather(
    @SerializedName("dt")
    val dateTime: Long,
    @SerializedName("main")
    val temperature: Temperature,
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("wind")
    val wind: Wind,
    @SerializedName("dt_txt")
    val dateTimeString: String
)
