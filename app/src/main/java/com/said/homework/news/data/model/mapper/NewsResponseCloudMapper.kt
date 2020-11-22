package com.said.homework.news.data.model.mapper

import com.said.homework.news.data.model.mapper.ArticleCloudMapper.map
import com.said.homework.news.data.model.response.GetNewsResponse
import com.said.homework.news.domain.entity.NewsResponseEntity

/**
 * Created by Ahmed Sa'eed on 22/11/2020.
 */
object NewsResponseCloudMapper {
    fun map(newsCloud: GetNewsResponse): NewsResponseEntity {
        val newsResponseEntity = NewsResponseEntity()
        newsResponseEntity.status = newsCloud.status
        newsResponseEntity.total = newsCloud.totalResults
        newsResponseEntity.articleEntities = map(newsCloud.articleClouds!!)
        return newsResponseEntity
    }
}