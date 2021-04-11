package com.dicoding.submission2teddy.di.ui.main.profile

import com.dicoding.submission2teddy.data.network.api.ApiHelper
import com.dicoding.submission2teddy.data.repositories.ProfileRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

// Main - Profile Module; Keyword : Dagger2
@Module
object ProfileModule {

    @Singleton
    @JvmStatic
    @Provides
    fun provideProfileRepository(
        api: ApiHelper
    ) = ProfileRepository(api)
}