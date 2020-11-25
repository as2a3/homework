package com.said.homework.news.presentation.model

import java.io.Serializable

/**
 * Created by Ahmed Sa'eed on 25,November,2020
 */
class NewsUI : Serializable{
    var total: Int? = null
    var articleUIS: MutableList<ArticleUI> = mutableListOf()
}