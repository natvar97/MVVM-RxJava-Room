package com.indialone.mvvmandrxjava.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.indialone.mvvmandrxjava.databinding.ActivityLoadNewsBinding
import com.indialone.mvvmandrxjava.viewmodel.NewsViewModel

class LoadNewsActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityLoadNewsBinding
    private lateinit var newsRecyclerAdapter: NewsRecyclerAdapter
    private lateinit var newsViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityLoadNewsBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        newsRecyclerAdapter = NewsRecyclerAdapter()
        mBinding.newsRecyclerView.layoutManager = LinearLayoutManager(this)
        mBinding.newsRecyclerView.adapter = newsRecyclerAdapter

        newsViewModel.getNews()

        mBinding.srlNotes.setOnRefreshListener {
            mBinding.srlNotes.isRefreshing = true
            newsViewModel.getNews()
            mBinding.srlNotes.isRefreshing = false
        }

        newsViewModel.getAllNews.observe(this, { list ->
            newsRecyclerAdapter.setData(list)
        })

    }
}