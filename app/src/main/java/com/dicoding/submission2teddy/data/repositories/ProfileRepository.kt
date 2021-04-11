package com.dicoding.submission2teddy.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.submission2teddy.data.model.Status
import com.dicoding.submission2teddy.data.model.UserDetail
import com.dicoding.submission2teddy.data.model.UserSearch
import com.dicoding.submission2teddy.data.network.api.ApiHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

// Profile Repository; Keyword : Repository
class ProfileRepository @Inject constructor(
    val api: ApiHelper
) {

    private val compositeDisposable = CompositeDisposable()

    // Function : for get data detail user from api
    fun getDetail(username: String): LiveData<Status<UserDetail>> {
        val liveData = MutableLiveData<Status<UserDetail>>()

        compositeDisposable.add(
            api.getUser(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {
                        it?.let {
                            liveData.postValue(Status.success(it))
                            return@subscribeBy
                        }
                        liveData.postValue(Status.error("Error", null))
                    },
                    onError = {
                        liveData.postValue(Status.error("Error : ${it.message}", null))
                    }
                )
        )

        return liveData
    }

    // Function : for get data list followers user from api
    fun getFollowersUser(username: String): LiveData<Status<List<UserSearch>>> {
        val liveData = MutableLiveData<Status<List<UserSearch>>>()

        compositeDisposable.add(
            api.getUserFollowers(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {
                        it?.let {
                            liveData.postValue(Status.success(it))
                            return@subscribeBy
                        }
                        liveData.postValue(Status.error("Error", null))
                    },
                    onError = {
                        liveData.postValue(Status.error("Error : ${it.message}", null))
                    }
                )
        )

        return liveData
    }

    // Function : for get data list following user from api
    fun getFollowingUser(username: String): LiveData<Status<List<UserSearch>>> {
        val liveData = MutableLiveData<Status<List<UserSearch>>>()

        compositeDisposable.add(
            api.getUserFollowing(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {
                        it?.let {
                            liveData.postValue(Status.success(it))
                            return@subscribeBy
                        }
                        liveData.postValue(Status.error("Error", null))
                    },
                    onError = {
                        liveData.postValue(Status.error("Error : ${it.message}", null))
                    }
                )
        )

        return liveData
    }

    // Function : for dispose profile repository composite
    fun disposeComposite() {
        compositeDisposable.dispose()
    }
}