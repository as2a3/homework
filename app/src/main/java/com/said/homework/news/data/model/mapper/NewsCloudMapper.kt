package com.said.homework.news.data.model.mapper

import com.said.homework.news.data.model.mapper.ArticleCloudMapper.map
import com.said.homework.news.data.model.NewsCloud
import com.said.homework.news.domain.entity.NewsEntity

/**
 * Created by Ahmed Sa'eed on 22/11/2020.
 */
object NewsCloudMapper {
    fun map(newsCloud: NewsCloud): NewsEntity {
        val newsEntity = NewsEntity()
        newsEntity.status = newsCloud.status
        newsEntity.total = newsCloud.totalResults
        newsEntity.articleEntities = map(newsCloud.articleClouds!!)
        return newsEntity
    }
}