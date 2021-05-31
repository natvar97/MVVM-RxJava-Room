package com.indialone.mvvmandrxjava.network.Entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class News {
    @Expose
    @SerializedName("articles")
    var articles: List<Articles>? = null

    @Expose
    @SerializedName("totalResults")
    var totalResults = 0

    @Expose
    @SerializedName("status")
    var status: String? = null
}