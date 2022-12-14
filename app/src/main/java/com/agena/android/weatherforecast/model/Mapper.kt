package com.agena.android.weatherforecast.model

internal interface Mapper<in T, out R> {

    fun map(origin: T): R

    fun map(origin: List<T>): List<R> {
        return origin.map(::map)
    }
}
