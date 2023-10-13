package com.cleancompose.use_case.data

import com.cleancompose.use_case.data.source.WeatherLocalDataSource
import com.cleancompose.use_case.data.source.WeatherRemoteDataSource
import io.reactivex.Flowable
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherRemoteDataSource: WeatherRemoteDataSource,
    private val weatherLocalDataSource: WeatherLocalDataSource
) {
    fun getDailyWeatherList(latitude: Float, longitude: Float, count: Int, units: String, appId: String): Flowable<List<com.cleancompose.entity.weather.Weather>> =
        weatherRemoteDataSource.getDailyWeatherList(latitude, longitude, count, units, appId)


    fun getHourlyWeatherList(latitude: Float, longitude: Float, count: Int, units: String, appId: String): Flowable<List<com.cleancompose.entity.weather.Weather>> =
        weatherRemoteDataSource.getHourlyWeatherList(latitude, longitude, count, units, appId).startWith(weatherLocalDataSource.getDailyWeatherList())
}
