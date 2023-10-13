package com.cleancompose.interface_adapter.model

data class WeatherRemote(
    val dt: Long?,
    val weather: List<WeatherRemoteInfo>
)