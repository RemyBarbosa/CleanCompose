package com.cleancompose.weather

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.cleancompose.application.NavigationGraphRoutes
import com.cleancompose.interface_adapter.DailyWeatherViewModel
import com.cleancompose.interface_adapter.DailyWeatherViewModel.DailyWeatherListState
import com.cleancompose.interface_adapter.base.ErrorState
import com.cleancompose.interface_adapter.base.LoadingState
import com.cleancompose.interface_adapter.model.WeatherUIModel

const val LATITUDE = 48.853f
const val LONGITUDE = 2.3488f
const val COUNT = 10
const val UNITS = "metric"
const val APP_ID = "e373fbdfb7c805a59762e6388e9ede6b"

@Composable
fun WeatherListScreen(navController: NavHostController) {
    val viewModel = hiltViewModel<DailyWeatherViewModel>()
    val state by viewModel.states.observeAsState()

    LaunchedEffect(key1 = true) { // constant key then the code is only call on init
        viewModel.initDailyWeather(
            latitude = LATITUDE,
            longitude = LONGITUDE,
            count = COUNT,
            units = UNITS,
            appId = APP_ID
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

            is DailyWeatherListState -> {
                val weatherList = (state as DailyWeatherListState).weatherUIModelList
                WeatherList(
                    weatherList,
                    modifier = Modifier.align(Alignment.TopCenter)
                ) {
                    navController.navigate(
                        NavigationGraphRoutes.WEATHER_DETAIL.routeName +
                                "/$LATITUDE/" +
                                "$LONGITUDE/" +
                                "$COUNT/" +
                                "$UNITS/" +
                                APP_ID
                    )
                }
            }
        }
    }
}

@Composable
fun WeatherList(
    weatherList: List<WeatherUIModel>,
    modifier: Modifier = Modifier,
    onItemClick: (() -> Unit)? = null
) {
    LazyColumn(modifier = modifier) {
        items(weatherList) { weather ->
            WeatherListItem(weatherUIModel = weather, onItemClick)
        }
    }
}

