package com.agena.android.weatherforecast.data.network

import com.agena.android.weatherforecast.data.model.ForecastResponse
import com.agena.android.weatherforecast.data.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {

    @GET("weather")
    suspend fun getCurrentWeather(
        @Query(LATITUDE) lat: Float,
        @Query(LONGITUDE) long: Float,
        @Query(UNITS) units: String = METRIC
    ): Response<WeatherResponse>

    @GET("weather")
    suspend fun getCurrentWeatherById(
        @Query(ID) id: Int,
        @Query(UNITS) units: String = METRIC
    ): Response<WeatherResponse>

    @GET("forecast")
    suspend fun getForecastById(
        @Query(ID) id: Int,
        @Query(UNITS) units: String = METRIC
    ): Response<ForecastResponse>
}
