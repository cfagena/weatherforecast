package com.agena.android.weatherforecast.data.model

import com.google.gson.annotations.SerializedName

data class ForecastResponse(
    @SerializedName("list")
    val weather: List<ForecastWeather>,
    @SerializedName("city")
    val city: City
)
