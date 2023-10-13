package com.cleancompose.use_case

import com.cleancompose.entity.weather.Weather
import com.cleancompose.use_case.data.WeatherRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow

class GetHourlyWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    fun execute(
        latitude: Float,
        longitude: Float,
        count: Int,
        units: String,
        appId: String
    ): Flow<List<Weather>> {
        return weatherRepository.getHourlyWeatherList(latitude, longitude, count, units, appId)
            .asFlow()
    }
}
