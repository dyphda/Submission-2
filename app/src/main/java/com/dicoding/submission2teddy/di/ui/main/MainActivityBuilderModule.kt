package com.dicoding.submission2teddy.di.ui.main

import com.dicoding.submission2teddy.di.HomeScope
import com.dicoding.submission2teddy.di.ProfileScope
import com.dicoding.submission2teddy.di.ui.main.home.HomeModule
import com.dicoding.submission2teddy.di.ui.main.profile.ProfileModule
import com.dicoding.submission2teddy.ui.main.home.HomeFragment
import com.dicoding.submission2teddy.ui.main.profile.ProfileFragment
import com.dicoding.submission2teddy.ui.main.profile.follow.ProfileFollowFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

// Main Activity Builder Module; Keyword : Dagger2
@Module
abstract class MainActivityBuilderModule {

    @HomeScope
    @ContributesAndroidInjector(
        modules = [
            HomeModule::class
        ]
    )
    abstract fun contributeHomeFragment(): HomeFragment

    @ProfileScope
    @ContributesAndroidInjector(
        modules = [
            ProfileModule::class
        ]
    )
    abstract fun contributeProfileFragment(): ProfileFragment

    @ProfileScope
    @ContributesAndroidInjector(
        modules = [
            ProfileModule::class
        ]
    )
    abstract fun contributeProfileFollowFragment(): ProfileFollowFragment
}