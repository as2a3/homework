package com.said.homework.news.data.model.mapper

import com.said.homework.news.data.model.ArticleCloud
import com.said.homework.news.data.model.mapper.ArticleSourceCloudMapper.map
import com.said.homework.news.domain.entity.ArticleEntity
import java.util.*

/**
 * Created by Ahmed Sa'eed on 22/11/2020.
 */
object ArticleCloudMapper {
    private fun map(articleCloud: ArticleCloud): ArticleEntity {
        val articleEntity = ArticleEntity()
        articleEntity.articleSourceEntity = map(articleCloud.articleSourceCloud!!)
        articleEntity.author = articleCloud.author
        articleEntity.title = articleCloud.title
        articleEntity.description = articleCloud.description
        articleEntity.publishAt = articleCloud.publishedAt
        articleEntity.content = articleCloud.content
        articleEntity.url = articleCloud.url
        articleEntity.urlToImage = articleCloud.urlToImage
        return articleEntity
    }

    fun map(articleClouds: List<ArticleCloud>): List<ArticleEntity> {
        val articleEntities: MutableList<ArticleEntity> = ArrayList()
        for (articleCloud in articleClouds) articleEntities.add(map(articleCloud))
        return articleEntities
    }
}