package com.agena.android.weatherforecast.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherEntity(
    val cityId: Int,
    val cityName: String,
    val temperature: Float,
    val feelsLike: Float,
    val weatherMain: String,
    val weatherDescription: String,
    val iconId: String,
    val windSpeed: Float,
    val windDirection: Int,
    val timezone: Int,
    val sunrise: Long,
    val sunset: Long
) : Parcelable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as WeatherEntity

        if (cityId != other.cityId) return false

        return true
    }
}
