package com.indialone.mvvmandrxjava.roomdatabase

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface UserDao {

    @Insert
    fun insert(user: User) : Completable

    @Delete
    fun delete(user: User): Completable

    @Update
    fun update(user: User): Completable

    @Query("SELECT * FROM users")
    fun getAllUsers(): Flowable<List<User>>

}