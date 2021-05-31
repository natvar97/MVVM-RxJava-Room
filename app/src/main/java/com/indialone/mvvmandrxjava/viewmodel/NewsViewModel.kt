package com.indialone.mvvmandrxjava.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.indialone.mvvmandrxjava.network.Entity.Articles
import com.indialone.mvvmandrxjava.network.NewsApiService
import com.indialone.mvvmandrxjava.repository.NewsRepository
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NewsViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {

//    private val newsApiService = NewsApiService()
//    private val compositeDisposable = CompositeDisposable()

    val getAllNews = MutableLiveData<List<Articles>>()

    val observable = newsRepository.getNews()
        .map { result ->
            Observable.fromIterable(result.articles)
        }
        .flatMap { list -> list }
        .toList().toObservable()

    @SuppressLint("CheckResult")
    fun getNews() {
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : Observer<List<Articles>> {
                override fun onSubscribe(d: Disposable) {}

                override fun onNext(t: List<Articles>) {
                    getAllNews.value = t
                }

                override fun onError(e: Throwable) {}

                override fun onComplete() {}

            })
    }

}