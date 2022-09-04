package com.agena.android.weatherforecast.model

import com.agena.android.weatherforecast.data.model.WeatherResponse

object WeatherToWeatherEntityMapper : Mapper<WeatherResponse, WeatherEntity> {

    override fun map(origin: WeatherResponse): WeatherEntity {
        return WeatherEntity(
            cityId = origin.cityId,
            cityName = origin.cityName,
            temperature = origin.temperature.temp,
            feelsLike = origin.temperature.feelsLike,
            weatherMain = origin.weather.first().main,
            weatherDescription = origin.weather.first().description,
            iconId = origin.weather.first().icon,
            windSpeed = origin.wind.speed,
            windDirection = origin.wind.deg,
            timezone = origin.timezone,
            sunrise = origin.system.sunrise,
            sunset = origin.system.sunset
        )
    }
}
