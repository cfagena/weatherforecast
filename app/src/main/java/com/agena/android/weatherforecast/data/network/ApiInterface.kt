package com.agena.android.weatherforecast.data.network

import com.agena.android.weatherforecast.data.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET(CURRENT_WEATHER_ENDPOINT)
    suspend fun getCurrentWeather(
        @Query(LATITUDE) lat: String,
        @Query(LONGITUDE) long: String,
        @Query(UNITS) units: String = METRIC
    ): Response<WeatherResponse>
}
