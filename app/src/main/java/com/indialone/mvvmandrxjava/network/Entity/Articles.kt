package com.indialone.mvvmandrxjava.network.Entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Articles {
    @Expose
    @SerializedName("content")
    var content: String? = null

    @Expose
    @SerializedName("publishedAt")
    var publishedAt: String? = null

    @Expose
    @SerializedName("urlToImage")
    var urlToImage: String? = null

    @Expose
    @SerializedName("url")
    var url: String? = null

    @Expose
    @SerializedName("description")
    var description: String? = null

    @Expose
    @SerializedName("title")
    var title: String? = null

    @Expose
    @SerializedName("author")
    var author: String? = null

    @Expose
    @SerializedName("source")
    var source: Source? = null
}