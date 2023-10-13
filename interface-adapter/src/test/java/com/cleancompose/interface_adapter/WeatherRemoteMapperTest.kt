package com.cleancompose.interface_adapter

import com.cleancompose.entity.weather.WeatherKind
import com.cleancompose.interface_adapter.model.WeatherRemote
import com.cleancompose.interface_adapter.model.WeatherRemoteInfo
import com.cleancompose.interface_adapter.model.WeatherRemoteMapper
import com.tngtech.java.junit.dataprovider.DataProvider
import com.tngtech.java.junit.dataprovider.DataProviderRunner
import com.tngtech.java.junit.dataprovider.UseDataProvider
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(DataProviderRunner::class)
class WeatherRemoteMapperTest {

    private val mapper = WeatherRemoteMapper()

    companion object {
        @DataProvider
        @JvmStatic
        @Suppress("unused")
        fun parameters(): Array<Array<Any?>> {
            return arrayOf(
                arrayOf("Rain", WeatherKind.RAIN),
                arrayOf("Clouds", WeatherKind.BROKEN_CLOUDS),
                arrayOf("Snow", WeatherKind.SNOW),
                arrayOf("Clear", WeatherKind.CLEAR)
            )
        }
    }

    @Test
    @UseDataProvider("parameters")
    fun map(remoteKind: String?, expectedWeatherKind: WeatherKind) {
        // Given
        val weatherRemote = WeatherRemote(
            dt = 3,
            weather = listOf(
                WeatherRemoteInfo(
                    main = remoteKind,
                    description = "description",
                    icon = "04d"
                )
            )
        )


        // When
        val weather = mapper.toEntity(weatherRemote)

        // Then

        with(weather) {
            assertThat(dateTime).isEqualTo(3)
            assertThat(kind).isEqualTo(expectedWeatherKind)
            assertThat(description).isEqualTo("description")
        }
    }
}