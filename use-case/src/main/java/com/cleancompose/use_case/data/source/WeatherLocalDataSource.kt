package com.cleancompose.use_case.data.source

import com.cleancompose.entity.weather.Weather
import io.reactivex.Flowable

interface WeatherLocalDataSource {
    fun getDailyWeatherList(): Flowable<List<Weather>>
    fun getHourlyWeatherList(): Flowable<List<Weather>>
}
