package com.cleancompose.weather.data.local

import com.cleancompose.entity.weather.Weather
import com.cleancompose.entity.weather.WeatherKind
import com.cleancompose.use_case.data.source.WeatherLocalDataSource
import io.reactivex.Flowable
import java.util.Date
import javax.inject.Inject

class WeatherLocalDataSourceImpl @Inject constructor() : WeatherLocalDataSource {

    override fun getDailyWeatherList(): Flowable<List<Weather>> {
        return Flowable.just(
            listOf(
                Weather(
                    dateTime = Date().time,
                    kind = WeatherKind.CLEAR,
                    description = "everythingIsClear"
                )
            )
        )
    }

    override fun getHourlyWeatherList(): Flowable<List<Weather>> {
        return Flowable.just(
            listOf(
                Weather(
                    dateTime = Date().time,
                    kind = WeatherKind.CLEAR,
                    description = "everythingIsClear"
                )
            )
        )
    }
}
