package com.indialone.mvvmandrxjava.repository

import com.indialone.mvvmandrxjava.network.NewsApiService

class NewsRepository(
    private val apiService: NewsApiService
) {

    fun getNews() = apiService.getNews()

}