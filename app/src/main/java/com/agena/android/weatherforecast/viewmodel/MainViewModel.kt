package com.agena.android.weatherforecast.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agena.android.weatherforecast.data.CurrentWeatherRepository
import com.agena.android.weatherforecast.data.ResultData
import com.agena.android.weatherforecast.model.ForecastEntity
import com.agena.android.weatherforecast.model.ForecastWeatherToForecastEntityMapper
import com.agena.android.weatherforecast.model.WeatherEntity
import com.agena.android.weatherforecast.model.WeatherToWeatherEntityMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainViewModel(
    private val currentWeatherRepository: CurrentWeatherRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    companion object {
        val TAG: String = this::class.java.declaringClass.simpleName
    }

    private val citiesIdList = listOf(
        2711537,
        2673730,
        5375480,
        2643743,
        5128581,
        2950159
    )

    private val _weather = MutableLiveData<List<WeatherEntity>>()
    val weather: LiveData<List<WeatherEntity>> = _weather

    fun getCurrentWeather() {
        val completenessMap = citiesIdList.map { it to false }.toMap()

        viewModelScope.launch(dispatcher) {
            val deferreds: List<Deferred<WeatherEntity?>> = completenessMap.keys.map { id ->
                async {
                    when (val result = currentWeatherRepository.getCurrentWeatherByCityId(id)) {
                        is ResultData.Success -> {
                            Log.d(TAG, "getCurrentWeather success ${result.value.cityName}")
                            WeatherToWeatherEntityMapper.map(result.value)
                        }
                        is ResultData.Loading -> {
                            Log.d(TAG, "Loading getCurrentWeather")
                            null
                        }
                        is ResultData.Failure -> {
                            Log.e(TAG, "Error loading current weather ${result.message}")
                            null
                        }
                    }
                }
            }
            deferreds.awaitAll().filterNotNull().let {
                _weather.postValue(it)
            }
        }
    }

    fun getWeatherForecast(cityId: Int): Flow<ForecastEntity?> {
        return flow<ForecastEntity?> {
            when (val result = currentWeatherRepository.getWeatherForecastByCityId(cityId)) {
                is ResultData.Success -> {
                    Log.d(TAG, "getCurrentWeather success ${result.value.city.cityName}")
                    emit(ForecastWeatherToForecastEntityMapper.map(result.value))
                }
                is ResultData.Loading -> {
                    Log.d(TAG, "Loading weather forecast")
                    emit(null)
                }
                is ResultData.Failure -> {
                    Log.e(TAG, "Error loading weather forecast ${result.message}")
                    emit(null)
                }
            }
        }
    }
}
