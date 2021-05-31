package com.indialone.mvvmandrxjava.network

import com.indialone.mvvmandrxjava.network.Entity.News
import com.indialone.mvvmandrxjava.utils.Constants
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsApiService {

    private var INSTANCE: NewsApi? = null

    private fun getInstance(): NewsApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return retrofit.create(NewsApi::class.java)
    }

    fun getNews(): Observable<News> {
        return NewsApiService().getInstance().getNews(
            Constants.NAME,
            Constants.FROM,
            Constants.TO,
            Constants.SORT_BY,
            Constants.API_KEY
        )
    }

}