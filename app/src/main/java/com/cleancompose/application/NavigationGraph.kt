package com.cleancompose.application

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cleancompose.weather.WeatherDetailScreen
import com.cleancompose.weather.WeatherListScreen

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = NavigationGraphRoutes.WEATHER_LIST.routeName) {
        composable(NavigationGraphRoutes.WEATHER_LIST.routeName) { WeatherListScreen(navController) }
        composable(NavigationGraphRoutes.WEATHER_DETAIL.routeName + "/{latitude}/{longitude}/{count}/{units}/{appId}") { backStackEntry ->
            val latitude = backStackEntry.arguments?.getFloat("latitude") ?: return@composable
            val longitude = backStackEntry.arguments?.getFloat("longitude") ?: return@composable
            val count = backStackEntry.arguments?.getInt("count") ?: return@composable
            val units = backStackEntry.arguments?.getString("units") ?: return@composable
            val appId = backStackEntry.arguments?.getString("appId") ?: return@composable
            WeatherDetailScreen(
                latitude,
                longitude,
                count,
                units,
                appId
            )
        }
    }
}

enum class NavigationGraphRoutes(val routeName: String) {
    WEATHER_LIST("weather_list"),
    WEATHER_DETAIL("weather_detail")
}