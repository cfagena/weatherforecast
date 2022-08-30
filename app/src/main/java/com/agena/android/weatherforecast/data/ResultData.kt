package com.agena.android.weatherforecast.data

sealed class ResultData<out T> {

    data class Success<out T>(val value: T) : ResultData<T>()
    data class Failure<out T>(val message: String, val errorCode: Int) : ResultData<T>()
    data class Loading<out T>(val value: T? = null) : ResultData<T>()

    companion object {
        fun <T> loading(value: T?): ResultData<T> =
            Loading(
                value
            )

        fun <T> success(value: T): ResultData<T> =
            Success(
                value = value
            )

        fun <T> failure(error_msg: String, error_code: Int): ResultData<T> =
            Failure(
                error_msg,
                error_code
            )
    }
}
