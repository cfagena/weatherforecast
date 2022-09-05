package com.agena.android.weatherforecast.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.agena.android.weatherforecast.TestCoroutineRule
import com.agena.android.weatherforecast.data.CurrentWeatherRepository
import com.agena.android.weatherforecast.data.ResultData
import com.agena.android.weatherforecast.data.model.Coordinate
import com.agena.android.weatherforecast.data.model.System
import com.agena.android.weatherforecast.data.model.Temperature
import com.agena.android.weatherforecast.data.model.Weather
import com.agena.android.weatherforecast.data.model.WeatherResponse
import com.agena.android.weatherforecast.data.model.Wind
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import kotlin.random.Random

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    var testCoroutineRule = TestCoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repository = mockk<CurrentWeatherRepository>()
    private val subject = MainViewModel(repository, testCoroutineRule.testCoroutineDispatcher)

    @Test
    fun `given called getCurrentWeather test if weather livedata loads current weather info`() = testCoroutineRule.runTest {
        val list = mutableListOf<Int>()

        coEvery {
            repository.getCurrentWeatherByCityId(id = capture(list))
        } answers {
            ResultData.success(getWeatherResponse())
        }

        subject.weather.observeForever {
            Assert.assertEquals(6, it.size)
            Assert.assertEquals("Goteborg", it.first().cityName)
        }
        subject.getCurrentWeather()
    }

    private fun getWeatherResponse(): WeatherResponse {
        return WeatherResponse(
            coord = Coordinate(1f, 2f),
            weather = listOf(
                Weather(
                    id = 1,
                    main = "",
                    description = "",
                    icon = ""
                )
            ),
            temperature = Temperature(
                temp = 1f,
                feelsLike = 1f,
                min = 1f,
                max = 1f,
                pressure = 1,
                humidity = 1
            ),
            wind = Wind(
                speed = 1f,
                deg = 90,
                gust = 1f
            ),
            system = System(
                country = "Sverige",
                sunrise = 1L,
                sunset = 1L
            ),
            cityId = Random.nextInt(),
            cityName = "Goteborg",
            timezone = 900
        )
    }
}
