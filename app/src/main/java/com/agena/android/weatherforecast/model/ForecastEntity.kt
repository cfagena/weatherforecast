package com.agena.android.weatherforecast.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ForecastEntity(
    val cityId: Int,
    val cityName: String,
    val temperatureList: List<TemperatureEntity>,
) : Parcelable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as ForecastEntity

        if (cityId != other.cityId) return false

        return true
    }
}
