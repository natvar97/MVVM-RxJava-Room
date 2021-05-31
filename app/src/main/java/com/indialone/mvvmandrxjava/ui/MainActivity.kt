package com.indialone.mvvmandrxjava.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.indialone.mvvmandrxjava.R
import com.indialone.mvvmandrxjava.databinding.ActivityMainBinding
import com.indialone.mvvmandrxjava.roomdatabase.User
import com.indialone.mvvmandrxjava.utils.MyApplication
import com.indialone.mvvmandrxjava.viewmodel.UserViewModel
import com.indialone.mvvmandrxjava.viewmodel.ViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var userViewModel: UserViewModel
    private lateinit var userRecyclerAdapter: UserRecyclerAdapter
    private lateinit var mBinding: ActivityMainBinding
    private var compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        userViewModel = ViewModelProvider(
            this,
            ViewModelFactory((application as MyApplication).repository)
        ).get(UserViewModel::class.java)

        mBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerAdapter = UserRecyclerAdapter(this)
        mBinding.recyclerView.adapter = userRecyclerAdapter

        compositeDisposable.add(
            userViewModel.getAllUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { usersList ->
                    Log.e("tag list", "$usersList[0]")
                    userRecyclerAdapter.addAll(usersList)
                }
        )

        mBinding.addUserFab.setOnClickListener(this)
        mBinding.ivNews.setOnClickListener(this)
    }

    /*
        override fun onStart() {
            super.onStart()

            compositeDisposable.add(
                userViewModel.getAllUsers()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { usersList ->
                        Log.e("tag list" , "$usersList[0]")
                        userRecyclerAdapter.addAll(usersList)
                    }
            )
        }
    */


    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }

    override fun onClick(v: View?) {
        v?.id?.let {
            when (v.id) {
                R.id.addUserFab -> {
                    startActivity(Intent(this, AddUserActivity::class.java))
                }
                R.id.iv_news -> {
                    startActivity(Intent(this, LoadNewsActivity::class.java))
                }
            }
        }
    }

    fun deleteUser(user: User) {
        compositeDisposable.add(
            userViewModel.delete(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        )
    }

}