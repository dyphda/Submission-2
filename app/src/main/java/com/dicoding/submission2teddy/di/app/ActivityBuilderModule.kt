package com.dicoding.submission2teddy.di.app

import com.dicoding.submission2teddy.di.ui.main.MainActivityBuilderModule
import com.dicoding.submission2teddy.di.ui.main.MainModule
import com.dicoding.submission2teddy.di.ui.main.MainViewModelModule
import com.dicoding.submission2teddy.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

// Activity Builder Module; Keyword : Dagger2
@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(
        modules = [
            MainActivityBuilderModule::class,
            MainViewModelModule::class,
            MainModule::class
        ]
    )
    abstract fun contributeMainActivity(): MainActivity

}