package com.said.homework.news.presentation.model.mapper

import com.said.homework.news.domain.entity.ArticleSourceEntity
import com.said.homework.news.presentation.model.ArticleSourceUI

/**
 * Created by Ahmed Sa'eed on 25,November,2020
 */
object ArticleSourceUIMapper {
    fun map(articleSourceEntity: ArticleSourceEntity): ArticleSourceUI {
        val articleSourceUI = ArticleSourceUI()
        articleSourceUI.id = articleSourceEntity.id
        articleSourceUI.name = articleSourceEntity.name
        return articleSourceUI
    }

    fun map(articleSourceUI: ArticleSourceUI): ArticleSourceEntity {
        return ArticleSourceEntity(articleSourceUI.id, articleSourceUI.name)
    }
}