package com.agena.android.weatherforecast

import android.app.Application
import com.agena.android.weatherforecast.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class WeatherForecastApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Koin Android logger
            androidLogger()
            // Android context
            androidContext(this@WeatherForecastApplication)
            // use modules
            modules(networkModule)
        }
    }
}
