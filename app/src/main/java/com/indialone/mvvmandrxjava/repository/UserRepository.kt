package com.indialone.mvvmandrxjava.repository

import com.indialone.mvvmandrxjava.network.NewsApiService
import com.indialone.mvvmandrxjava.roomdatabase.User
import com.indialone.mvvmandrxjava.roomdatabase.UserDao
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class UserRepository(
    private val userDao: UserDao
) {

    fun insert(user: User) = userDao.insert(user)

    fun update(user: User) = userDao.update(user)

    fun delete(user: User) = userDao.delete(user)

    fun getAllUsers(): Flowable<List<User>> = userDao.getAllUsers()

}