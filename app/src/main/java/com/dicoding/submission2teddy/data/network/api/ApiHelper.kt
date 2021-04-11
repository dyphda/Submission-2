package com.dicoding.submission2teddy.data.network.api

import com.dicoding.submission2teddy.data.model.UserDetail
import com.dicoding.submission2teddy.data.model.UserSearch
import com.dicoding.submission2teddy.data.model.response.SearchResponse
import com.dicoding.submission2teddy.data.network.setting.BasicInterceptor
import com.dicoding.submission2teddy.data.util.ACCESS_TOKEN
import com.dicoding.submission2teddy.data.util.BASE_URL
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

// Api Interface; Keyword : Retrofit2
interface ApiHelper {

    companion object {
        private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        // Function : for configure retrofit and return ApiHelper
        fun create(): ApiHelper {
            val okHttp = OkHttpClient.Builder()
                .addInterceptor(BasicInterceptor(ACCESS_TOKEN))
                .addInterceptor(logger)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttp)
                .build()
                .create(ApiHelper::class.java)
        }
    }

    /* --- User --- */
    @GET("search/users")
    fun getSearchUser(
        @Query("q") keyword: String
    ): Observable<SearchResponse>

    @GET("users/{username}")
    fun getUser(
        @Path("username") username: String
    ): Observable<UserDetail>

    @GET("users/{username}/followers")
    fun getUserFollowers(
        @Path("username") username: String
    ): Observable<List<UserSearch>>

    @GET("users/{username}/following")
    fun getUserFollowing(
        @Path("username") username: String
    ): Observable<List<UserSearch>>
}