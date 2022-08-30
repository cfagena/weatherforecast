package com.agena.android.weatherforecast.data.model

import com.google.gson.annotations.SerializedName

data class Temperature(
    @SerializedName("temp")
    val temp: Float,
    @SerializedName("feels_like")
    val feelsLike: Float,
    @SerializedName("temp_min")
    val min: Float,
    @SerializedName("temp_max")
    val max: Float,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("humidity")
    val humidity: Int
)
