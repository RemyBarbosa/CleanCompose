package com.cleancompose.application

import com.cleancompose.use_case.data.source.WeatherLocalDataSource
import com.cleancompose.use_case.data.source.WeatherRemoteDataSource
import com.cleancompose.weather.data.local.WeatherLocalDataSourceImpl
import com.cleancompose.weather.data.remote.WeatherRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindWeatherLocalDataSource(
        impl: WeatherLocalDataSourceImpl
    ): WeatherLocalDataSource

    @Binds
    abstract fun bindWeatherRemoteDataSource(
        impl: WeatherRemoteDataSourceImpl
    ): WeatherRemoteDataSource
}