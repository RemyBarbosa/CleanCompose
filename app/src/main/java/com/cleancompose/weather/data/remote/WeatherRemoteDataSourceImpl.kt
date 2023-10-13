package com.cleancompose.weather.data.remote

import com.cleancompose.entity.weather.Weather
import com.cleancompose.interface_adapter.model.WeatherRemoteMapper
import com.cleancompose.use_case.data.source.WeatherRemoteDataSource
import io.reactivex.Flowable
import javax.inject.Inject

class WeatherRemoteDataSourceImpl @Inject constructor(
    private val mapper: WeatherRemoteMapper,
    private val weatherRetrofitDataSource: WeatherRetrofitDataSource
) : WeatherRemoteDataSource {

    override fun getDailyWeatherList(latitude: Float, longitude: Float, count: Int, units: String, appId: String): Flowable<List<Weather>> {
        return  weatherRetrofitDataSource.getDailyWeatherList(latitude, longitude, count, units, appId).map { dailyWeatherRemoteList ->
            dailyWeatherRemoteList.list.map {
                mapper.toEntity(it)
            }
        }
    }

    override fun getHourlyWeatherList(latitude: Float, longitude: Float, count: Int, units: String, appId: String): Flowable<List<Weather>> {
        return  weatherRetrofitDataSource.getHourlyWeatherList(latitude, longitude, count, units, appId).map { dailyWeatherRemoteList ->
            dailyWeatherRemoteList.list.map {
                mapper.toEntity(it)
            }
        }
    }
}
