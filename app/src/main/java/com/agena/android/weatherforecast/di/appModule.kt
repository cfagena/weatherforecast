package com.agena.android.weatherforecast.di

import com.agena.android.weatherforecast.data.CurrentWeatherRepository
import com.agena.android.weatherforecast.data.CurrentWeatherRepositoryImpl
import com.agena.android.weatherforecast.ui.CityWeatherAdapter
import com.agena.android.weatherforecast.viewmodel.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single {
        CityWeatherAdapter(
            context = androidContext()
        )
    }

    single<CurrentWeatherRepository> {
        CurrentWeatherRepositoryImpl(
            openWeatherApi = get()
        )
    }

    viewModel {
        MainViewModel(
            currentWeatherRepository = get()
        )
    }
}
