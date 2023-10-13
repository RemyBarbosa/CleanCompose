package com.cleancompose.weather

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.cleancompose.interface_adapter.HourlyWeatherViewModel
import com.cleancompose.interface_adapter.base.ErrorState
import com.cleancompose.interface_adapter.base.LoadingState

@Composable
fun WeatherDetailScreen(
    latitude: Float,
    longitude: Float,
    count: Int,
    units: String,
    appId: String
) {
    val viewModel = hiltViewModel<HourlyWeatherViewModel>()
    val state by viewModel.states.observeAsState()

    LaunchedEffect(key1 = true) { // constant key then the code is only call on init
        viewModel.getHourlyWeather(
            latitude = latitude,
            longitude = longitude,
            count = count,
            units = units,
            appId = appId
        )
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        when (state) {
            is LoadingState -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            is ErrorState -> {
                val error = (state as ErrorState).error
                val context = LocalContext.current
                LaunchedEffect(error) {
                    Toast.makeText(
                        context,
                        error.localizedMessage ?: "An unknown error occurred",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            is HourlyWeatherViewModel.HourlyWeatherListState -> {
                val weatherList =
                    (state as HourlyWeatherViewModel.HourlyWeatherListState).weatherUIModelList
                WeatherList(
                    weatherList,
                    modifier = Modifier.align(Alignment.TopCenter)
                )
            }
        }
    }

}
