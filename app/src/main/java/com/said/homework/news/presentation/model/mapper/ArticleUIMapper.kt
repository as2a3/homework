package com.said.homework.news.presentation.model.mapper

import com.said.homework.base.presentation.util.DateTimeUtil
import com.said.homework.base.presentation.util.DateTimeUtil.convertToDateString
import com.said.homework.news.domain.entity.ArticleEntity
import com.said.homework.news.presentation.model.ArticleUI
import com.said.homework.news.presentation.model.mapper.ArticleSourceUIMapper.map
import java.util.*

/**
 * Created by Ahmed Sa'eed on 25,November,2020
 */
object ArticleUIMapper {
    fun map(articleEntity: ArticleEntity): ArticleUI {
        val articleUI = ArticleUI()
        articleUI.articleSourceUI = articleEntity.articleSourceEntity?.let { map(it) }
        articleUI.author = articleEntity.author
        articleUI.title = articleEntity.title
        articleUI.description = articleEntity.description
        articleUI.content = articleEntity.content
        if (articleEntity.publishAt != null) articleUI.publishAt = convertToDateString(
            articleEntity.publishAt!!.time, DateTimeUtil.SERVER_DATE_TIME_FORMAT
        )
        articleUI.url = articleEntity.url
        articleUI.urlToImage = articleEntity.urlToImage
        return articleUI
    }

    @JvmStatic
    fun map(articleEntities: List<ArticleEntity>): MutableList<ArticleUI> {
        val articleUIS: MutableList<ArticleUI> = ArrayList()
        for (articleEntity in articleEntities) {
            articleUIS.add(map(articleEntity))
        }
        return articleUIS
    }
}