package com.agena.android.weatherforecast.di

import com.agena.android.weatherforecast.data.network.OpenWeatherApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    val baseURL = "https://api.openweathermap.org/data/2.5/"

    val mHttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    val apiKeyInterceptor = Interceptor { chain ->
        val original = chain.request()
        val originalHttpUrl = original.url

        // Do NOT try this at home: just saving the api key for exercise purposes
        // A better approach would be to save it in a environment variable
        val newUrl = originalHttpUrl.newBuilder().addQueryParameter(
            "appid",
            "b6cbb19642b16e2b1e0e2a645732f789"
        ).build()

        val requestBuilder = original.newBuilder().url(newUrl)
        val request = requestBuilder.build()

        return@Interceptor chain.proceed(request)
    }

    val mOkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(mHttpLoggingInterceptor)
        .addInterceptor(apiKeyInterceptor)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(mOkHttpClient)
        .build()

    single<OpenWeatherApi> {
        retrofit.create(OpenWeatherApi::class.java)
    }
}
