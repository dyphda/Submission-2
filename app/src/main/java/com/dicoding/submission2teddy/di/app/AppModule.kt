package com.dicoding.submission2teddy.di.app

import com.dicoding.submission2teddy.data.network.api.ApiHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

// App Module; Keyword : Dagger2
@Module
object AppModule {

    /* --- API --- */
    @Singleton
    @JvmStatic
    @Provides
    fun provideApiHelper(): ApiHelper =
        ApiHelper.create()
}