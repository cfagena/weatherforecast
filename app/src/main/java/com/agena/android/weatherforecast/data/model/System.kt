package com.agena.android.weatherforecast.data.model

import com.google.gson.annotations.SerializedName

data class System(
    @SerializedName("country")
    val country: String,
    @SerializedName("sunrise")
    val sunrise: Long,
    @SerializedName("sunset")
    val sunset: Long
)
