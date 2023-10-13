package com.cleancompose.interface_adapter.model

import android.text.format.DateUtils
import com.cleancompose.entity.weather.Weather
import com.cleancompose.entity.weather.WeatherKind
import com.cleancompose.interface_adapter.R
import java.util.Calendar
import javax.inject.Inject

class WeatherUIMapper @Inject constructor() {
    fun fromEntity(weather: Weather) = WeatherUIModel(
        date = DateUtils.getRelativeTimeSpanString(weather.dateTime * 1000, Calendar.getInstance().timeInMillis, DateUtils.MINUTE_IN_MILLIS).toString(),
        imageRes = getImageFromKind(weather.kind),
        description = weather.description
    )

    private fun getImageFromKind(kind: WeatherKind) = when (kind) {
        WeatherKind.RAIN -> R.drawable.ic_wb_rainny
        WeatherKind.CLEAR -> R.drawable.ic_wb_sunny
        WeatherKind.CLOUDS -> R.drawable.ic_wb_cloudy
        WeatherKind.BROKEN_CLOUDS -> R.drawable.ic_wb_light_sunny
        WeatherKind.SNOW -> R.drawable.ic_wb_snowy
    }
}