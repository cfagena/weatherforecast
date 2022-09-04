package com.agena.android.weatherforecast.data

import com.agena.android.weatherforecast.data.model.ForecastResponse
import com.agena.android.weatherforecast.data.model.WeatherResponse
import com.agena.android.weatherforecast.data.network.ERROR_CODE_UNKNOWN
import com.agena.android.weatherforecast.data.network.OpenWeatherApi

class CurrentWeatherRepositoryImpl(
    private val openWeatherApi: OpenWeatherApi
) : CurrentWeatherRepository {

    override suspend fun getCurrentWeather(lat: Float, long: Float): ResultData<WeatherResponse> {
        return runCatching {
            val response = openWeatherApi.getCurrentWeather(lat = lat, long = long)
            if (response.isSuccessful && response.body() != null) {
                ResultData.success(response.body()!!)
            } else {
                ResultData.failure(response.message(), response.code())
            }
        }.getOrElse {
            ResultData.failure(it.localizedMessage ?: "", ERROR_CODE_UNKNOWN)
        }
    }

    override suspend fun getCurrentWeatherByCityId(id: Int): ResultData<WeatherResponse> {
        return runCatching {
            val response = openWeatherApi.getCurrentWeatherById(id = id)
            if (response.isSuccessful && response.body() != null) {
                ResultData.success(response.body()!!)
            } else {
                ResultData.failure(response.message(), response.code())
            }
        }.getOrElse {
            ResultData.failure(it.localizedMessage ?: "", ERROR_CODE_UNKNOWN)
        }
    }

    override suspend fun getWeatherForecastByCityId(id: Int): ResultData<ForecastResponse> {
        return runCatching {
            val response = openWeatherApi.getForecastById(id = id)
            if (response.isSuccessful && response.body() != null) {
                ResultData.success(response.body()!!)
            } else {
                ResultData.failure(response.message(), response.code())
            }
        }.getOrElse {
            ResultData.failure(it.localizedMessage ?: "", ERROR_CODE_UNKNOWN)
        }
    }
}
