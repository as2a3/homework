package com.said.homework.news.data.model.response

import com.google.gson.annotations.SerializedName
import com.said.homework.base.data.model.BaseResponse
import com.said.homework.news.data.model.ArticleCloud

/**
 * Created by Ahmed Sa'eed on 22/11/2020.
 */
class GetNewsResponse : BaseResponse() {
    @SerializedName("articles")
    val articleClouds: List<ArticleCloud>? = null
}