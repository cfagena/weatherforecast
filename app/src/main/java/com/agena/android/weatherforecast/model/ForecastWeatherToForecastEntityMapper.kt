package com.agena.android.weatherforecast.model

import com.agena.android.weatherforecast.data.model.ForecastResponse

object ForecastWeatherToForecastEntityMapper : Mapper<ForecastResponse, ForecastEntity> {

    override fun map(origin: ForecastResponse): ForecastEntity {
        return ForecastEntity(
            cityId = origin.city.cityId,
            cityName = origin.city.cityName,
            temperatureList = mapTemperatureList(origin)
        )
    }

    private fun mapTemperatureList(origin: ForecastResponse): List<TemperatureEntity> {
        return origin.weather.map {
            TemperatureEntity(
                dateTime = it.dateTime,
                dateTimeString = it.dateTimeString,
                icon = it.weather.first().icon,
                temperature = it.temperature.temp
            )
        }
    }
}
