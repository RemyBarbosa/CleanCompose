package com.cleancompose.interface_adapter.model

import com.cleancompose.entity.weather.Weather
import com.cleancompose.entity.weather.WeatherKind
import javax.inject.Inject

class WeatherRemoteMapper @Inject constructor() {
    companion object {
        private const val DEFAULT_DESCRIPTION = "let the sunshine in"
    }

    fun toEntity(weatherRemote: WeatherRemote): Weather {
        val weatherRemoteInfo = weatherRemote.weather.getOrNull(0)
        return Weather(
            dateTime = weatherRemote.dt ?: 0,
            kind = getKind(weatherRemoteInfo?.main, weatherRemoteInfo?.icon),
            description = weatherRemoteInfo?.description ?: DEFAULT_DESCRIPTION
        )
    }

    private fun getKind(main: String?, icon: String?): WeatherKind {
        return when (main) {
            "Rain" -> WeatherKind.RAIN
            "Clouds" -> if (icon == "04d") WeatherKind.BROKEN_CLOUDS else WeatherKind.CLOUDS
            "Snow" -> WeatherKind.SNOW
            "Clear" -> WeatherKind.CLEAR
            else -> WeatherKind.CLEAR // i'm an optimistic dude ^^
        }
    }
}

