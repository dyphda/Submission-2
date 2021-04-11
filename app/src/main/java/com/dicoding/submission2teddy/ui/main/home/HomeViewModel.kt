package com.dicoding.submission2teddy.ui.main.home

import androidx.lifecycle.ViewModel
import com.dicoding.submission2teddy.data.repositories.HomeRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    // Function : for get data user search from API
    fun getUserSearch(keyword: String) = repository.getUserSearch(keyword)

    override fun onCleared() {
        repository.disposeComposite()
        super.onCleared()
    }
}
