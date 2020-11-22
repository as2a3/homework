package com.said.homework.news.domain.entity

import java.util.*

/**
 * Created by Ahmed Sa'eed on 22/11/2020.
 */
class ArticleEntity {
    var articleSourceEntity: ArticleSourceEntity? = null
    var author: String? = null
    var title: String? = null
    var description: String? = null
    var url: String? = null
    var urlToImage: String? = null
    var publishAt: Date? = null
    var content: String? = null
}