package com.cleancompose.interface_adapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cleancompose.interface_adapter.base.ErrorState
import com.cleancompose.interface_adapter.base.LoadingState
import com.cleancompose.interface_adapter.base.State
import com.cleancompose.interface_adapter.model.WeatherUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class HourlyWeatherViewModel  @Inject constructor(
    private val weatherManager: WeatherManager
) : ViewModel() {

    private val _states = MutableLiveData<State>()
    val states: LiveData<State> = _states

    fun getHourlyWeather(
        latitude: Float,
        longitude: Float,
        count: Int,
        units: String,
        appId: String
    ) = viewModelScope.launch {
        _states.value = LoadingState

        try {
            withContext(Dispatchers.IO) {
                weatherManager.getHourlyWeatherList(
                    latitude,
                    longitude,
                    count,
                    units,
                    appId
                ).catch { e ->
                    withContext(Dispatchers.Main) {
                        _states.value = ErrorState(e)
                    }
                }.collect { weatherList ->
                    withContext(Dispatchers.Main) {
                        _states.value = HourlyWeatherListState(weatherList)
                    }
                }
            }
        } catch (e: Exception) {
            _states.value = ErrorState(e)
        }
    }

    data class HourlyWeatherListState(val weatherUIModelList: List<WeatherUIModel>) : State()
}