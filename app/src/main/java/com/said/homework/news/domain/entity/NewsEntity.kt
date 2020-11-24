package com.said.homework.news.domain.entity

/**
 * Created by Ahmed Sa'eed on 22/11/2020.
 */
class NewsEntity {
    var status: String? = null
    var message: String? = null
    var total: Long? = null
    var articleEntities: List<ArticleEntity>? = null
}