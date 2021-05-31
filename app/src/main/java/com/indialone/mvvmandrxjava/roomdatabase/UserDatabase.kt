package com.indialone.mvvmandrxjava.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.indialone.mvvmandrxjava.utils.Constants

@Database(entities = [User::class], version = Constants.DATABASE_VERSION)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room
                    .databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        Constants.DATABASE_NAME
                    ).build()
            }
            return INSTANCE!!
        }

    }


}