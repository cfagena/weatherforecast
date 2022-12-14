package com.agena.android.weatherforecast.data.model

import com.google.gson.annotations.SerializedName

data class Coordinate(
    @SerializedName("lat")
    val latitude: Float,
    @SerializedName("lon")
    val longitude: Float
)
