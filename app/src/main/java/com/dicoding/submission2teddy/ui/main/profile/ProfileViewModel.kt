package com.dicoding.submission2teddy.ui.main.profile

import androidx.lifecycle.ViewModel
import com.dicoding.submission2teddy.data.repositories.ProfileRepository
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel() {

    // Function : for get data user detail from api
    fun getDetail(username: String) = repository.getDetail(username)

    override fun onCleared() {
        repository.disposeComposite()
        super.onCleared()
    }
}