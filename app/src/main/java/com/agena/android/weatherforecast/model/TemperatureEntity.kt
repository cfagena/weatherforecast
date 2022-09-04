package com.agena.android.weatherforecast.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TemperatureEntity(
    val dateTime: Long,
    val dateTimeString: String,
    val icon: String,
    val temperature: Float
) : Parcelable
