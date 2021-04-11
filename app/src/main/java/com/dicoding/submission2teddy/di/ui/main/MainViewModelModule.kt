package com.dicoding.submission2teddy.di.ui.main

import androidx.lifecycle.ViewModel
import com.dicoding.submission2teddy.di.viewmodel.ViewModelKey
import com.dicoding.submission2teddy.ui.main.MainViewModel
import com.dicoding.submission2teddy.ui.main.home.HomeViewModel
import com.dicoding.submission2teddy.ui.main.profile.ProfileViewModel
import com.dicoding.submission2teddy.ui.main.profile.follow.ProfileFollowViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

// Main View Model Module; Keyword : Dagger2
@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(profileViewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileFollowViewModel::class)
    abstract fun bindProfileFollowViewModel(profileFollowViewModel: ProfileFollowViewModel): ViewModel
}