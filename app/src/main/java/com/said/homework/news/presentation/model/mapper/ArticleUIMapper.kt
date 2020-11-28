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
        articleUI.localID = articleEntity.localID
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

    fun map(articleUI: ArticleUI):  ArticleEntity{
        val articleEntity = ArticleEntity()
        articleEntity.localID = articleUI.localID
        articleEntity.articleSourceEntity = map(articleUI.articleSourceUI!!)
        articleEntity.author = articleUI.author
        articleEntity.title = articleUI.title
        articleEntity.description = articleUI.description
        articleEntity.content = articleUI.content
        articleEntity.publishAt = DateTimeUtil.convertToDate(articleUI.publishAt, DateTimeUtil.SERVER_DATE_TIME_FORMAT)
        articleEntity.url = articleUI.url
        articleEntity.urlToImage = articleUI.urlToImage
        return articleEntity
    }

    fun map(articleEntities: MutableCollection<ArticleEntity>): MutableList<ArticleUI> {
        val articleUIS: MutableList<ArticleUI> = ArrayList()
        for (articleEntity in articleEntities) {
            articleUIS.add(map(articleEntity))
        }
        return articleUIS
    }
}