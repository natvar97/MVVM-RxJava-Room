package com.indialone.mvvmandrxjava.roomdatabase

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.indialone.mvvmandrxjava.utils.Constants
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = Constants.TABLE_NAME)
data class User(

    @ColumnInfo(name = "username")
    var username: String? = null,

    @ColumnInfo(name = "email")
    var email: String? = null,

    @ColumnInfo(name = "favourite")
    var favourite: String? = null,

    @ColumnInfo(name = "hobby")
    var hobby: String? = null,

    @ColumnInfo(name = "country")
    var country: String? = null,

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

) : Parcelable
