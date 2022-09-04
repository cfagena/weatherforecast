package com.agena.android.weatherforecast.data.model

import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("id")
    val cityId: Int,
    @SerializedName("name")
    val cityName: String,
    @SerializedName("coord")
    val coord: Coordinate,
    @SerializedName("timezone")
    val timezone: Int
)
