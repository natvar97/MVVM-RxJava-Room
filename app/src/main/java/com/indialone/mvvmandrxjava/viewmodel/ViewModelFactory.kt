package com.indialone.mvvmandrxjava.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.indialone.mvvmandrxjava.repository.NewsRepository
import com.indialone.mvvmandrxjava.repository.UserRepository
import java.lang.IllegalArgumentException

class ViewModelFactory(
    private val repository: UserRepository,
    private val newsRepository: NewsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(repository) as T
        }

        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            return NewsViewModel(newsRepository) as T
        }

        throw IllegalArgumentException("Unknown View Model class...")
    }
}