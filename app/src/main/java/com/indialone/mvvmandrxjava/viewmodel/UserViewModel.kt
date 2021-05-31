package com.indialone.mvvmandrxjava.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.indialone.mvvmandrxjava.repository.UserRepository
import com.indialone.mvvmandrxjava.roomdatabase.User
import io.reactivex.Flowable

class UserViewModel(
    private val repository: UserRepository
) : ViewModel() {

    private val users = MutableLiveData<List<User>>()

    fun insert(user: User) = repository.insert(user)


    fun update(user: User) = repository.update(user)

    fun delete(user: User) = repository.delete(user)

    fun getAllUsers(): Flowable<List<User>> {
        return repository.getAllUsers()
    }

}