package com.indialone.mvvmandrxjava.utils

import android.app.Application
import com.indialone.mvvmandrxjava.repository.UserRepository
import com.indialone.mvvmandrxjava.roomdatabase.UserDatabase

class MyApplication : Application() {

    private val database by lazy {
        UserDatabase.getDatabase(this@MyApplication)
    }

    val repository by lazy {
        UserRepository(database.userDao())
    }

}