package com.cleancompose.application

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object BaseUrlModule {

    @Provides
    @Named("base_url")
    fun provideBaseUrl(): String = "https://api.openweathermap.org/data/2.5/"
}
