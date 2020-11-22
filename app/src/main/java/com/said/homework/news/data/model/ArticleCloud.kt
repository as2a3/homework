package com.said.homework.news.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by Ahmed Sa'eed on 22/11/2020.
 */
class ArticleCloud {
    @SerializedName("source")
    val articleSourceCloud: ArticleSourceCloud? = null

    @SerializedName("author")
    val author: String? = null

    @SerializedName("title")
    val title: String? = null

    @SerializedName("description")
    val description: String? = null

    @SerializedName("url")
    val url: String? = null

    @SerializedName("urlToImage")
    val urlToImage: String? = null

    @SerializedName("publishedAt")
    val publishedAt: Date? = null

    @SerializedName("content")
    val content: String? = null
}