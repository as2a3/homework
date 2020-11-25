package com.said.homework.news.presentation.model.mapper

import com.said.homework.news.domain.entity.NewsEntity
import com.said.homework.news.presentation.model.NewsUI
import com.said.homework.news.presentation.model.mapper.ArticleUIMapper.map

/**
 * Created by Ahmed Sa'eed on 25,November,2020
 */
object NewsUIMapper {
    fun map(newsEntity: NewsEntity): NewsUI {
        val newsUI = NewsUI()
        newsUI.total = newsEntity.total
        newsUI.articleUIS = map(newsEntity.articleEntities!!)
        return newsUI
    }
}