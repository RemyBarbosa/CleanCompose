package com.cleancompose

import com.cleancompose.application.AppModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Named

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppModule::class]
)
object TestBaseUrlModule {

    @Provides
    @Named("base_url")
    fun provideBaseUrl(): String = "http://127.0.0.1:8080"
}
