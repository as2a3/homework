package com.said.homework.news.presentation.model

import com.said.homework.news.domain.entity.ArticleSourceEntity
import java.io.Serializable

/**
 * Created by Ahmed Sa'eed on 25,November,2020
 */
class ArticleUI : Serializable {
    var articleSourceUI: ArticleSourceUI? = null
    var author: String? = null
    var title: String? = null
    var description: String? = null
    var url: String? = null
    var urlToImage: String? = null
    var publishAt: String? = null
    var content: String? = null
}