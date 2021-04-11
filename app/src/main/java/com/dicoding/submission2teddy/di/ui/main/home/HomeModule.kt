package com.dicoding.submission2teddy.di.ui.main.home

import com.dicoding.submission2teddy.data.network.api.ApiHelper
import com.dicoding.submission2teddy.data.repositories.HomeRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

// Main - Home Module; Keyword : Dagger2
@Module
object HomeModule {

    @Singleton
    @JvmStatic
    @Provides
    fun provideHomeRepository(
        api: ApiHelper
    ) = HomeRepository(api)
}