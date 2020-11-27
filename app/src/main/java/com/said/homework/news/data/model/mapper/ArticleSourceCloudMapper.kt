package com.said.homework.news.data.model.mapper

import com.said.homework.news.data.model.ArticleSourceCloud
import com.said.homework.news.domain.entity.ArticleSourceEntity

/**
 * Created by Ahmed Sa'eed on 22/11/2020.
 */
object ArticleSourceCloudMapper {
    @JvmStatic
    fun map(articleSourceCloud: ArticleSourceCloud): ArticleSourceEntity {
        return ArticleSourceEntity(articleSourceCloud.id, articleSourceCloud.name)
    }
}