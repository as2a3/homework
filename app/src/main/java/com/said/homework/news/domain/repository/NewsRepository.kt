package com.said.homework.news.domain.repository

import com.said.homework.news.data.model.NewsCloud
import com.said.homework.news.domain.entity.ArticleEntity
import com.said.homework.news.domain.entity.GetNewsParamsEntity
import io.reactivex.Observable

/**
 * Created by Ahmed Sa'eed on 22/11/2020.
 */
interface NewsRepository {
    fun getArticles(getNewsParamsEntity: GetNewsParamsEntity?): Observable<NewsCloud?>?

    fun addArticleIntoDB(articleEntity: ArticleEntity?): Observable<Long?>?
}