package com.indialone.mvvmandrxjava.network

import com.indialone.mvvmandrxjava.network.Entity.News
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    fun getNews(
        @Query("q") name: String,
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("sortBy") sortBy: String,
        @Query("apiKey") apiKey: String
    ) : Observable<News>

}