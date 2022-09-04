package com.agena.android.weatherforecast.data

import com.agena.android.weatherforecast.data.model.ForecastResponse
import com.agena.android.weatherforecast.data.model.WeatherResponse

interface CurrentWeatherRepository {

    suspend fun getCurrentWeather(lat: Float, long: Float): ResultData<WeatherResponse>
    suspend fun getCurrentWeatherByCityId(id: Int): ResultData<WeatherResponse>
    suspend fun getWeatherForecastByCityId(id: Int): ResultData<ForecastResponse>
}
