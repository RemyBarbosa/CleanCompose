package com.cleancompose.interface_adapter

import com.cleancompose.interface_adapter.model.WeatherUIMapper
import com.cleancompose.interface_adapter.model.WeatherUIModel
import com.cleancompose.use_case.GetDailyWeatherUseCase
import com.cleancompose.use_case.GetHourlyWeatherUseCase
import java.util.Calendar
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class WeatherManager @Inject constructor(
    private val getDailyWeatherUseCase: GetDailyWeatherUseCase,
    private val getHourlyWeatherUseCase: GetHourlyWeatherUseCase,
    private val mapper: WeatherUIMapper
) {
    fun getDailyWeatherList(
        latitude: Float,
        longitude: Float,
        count: Int,
        units: String,
        appId: String
        ): Flow<List<WeatherUIModel>> {

        val fifteenDaysInMillis = 15 * 24 * 60 * 60 * 1000
        return getDailyWeatherUseCase.execute(latitude, longitude, count, units, appId).map { list ->
            list.filter {
                it.dateTime * 1000 < Calendar.getInstance().timeInMillis + fifteenDaysInMillis
            }.map { mapper.fromEntity(it) }
        }
    }

    fun getHourlyWeatherList(
        latitude: Float,
        longitude: Float,
        count: Int,
        units: String,
        appId: String
    ): Flow<List<WeatherUIModel>> {
        return getHourlyWeatherUseCase.execute(latitude, longitude, count, units, appId).map { list ->
            list.map { mapper.fromEntity(it) }
        }
    }
}
