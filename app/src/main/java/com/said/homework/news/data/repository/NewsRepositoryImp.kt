package com.said.homework.news.data.repository

import com.said.homework.news.data.db.ArticleDbManager
import com.said.homework.news.data.model.NewsCloud
import com.said.homework.news.data.model.mapper.ArticleDBMapper
import com.said.homework.news.data.network.NewsManagementCloud
import com.said.homework.news.domain.entity.ArticleEntity
import com.said.homework.news.domain.entity.GetNewsParamsEntity
import com.said.homework.news.domain.repository.NewsRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Ahmed Sa'eed on 22/11/2020.
 */
class NewsRepositoryImp @Inject constructor(private val newsCloud: NewsManagementCloud,
                                            private val articleDBMapper: ArticleDBMapper) : NewsRepository {


    override fun getArticles(getNewsParamsEntity: GetNewsParamsEntity?): Observable<NewsCloud?>? {
        return getNewsParamsEntity?.let { newsCloud.getNews(it) }
    }

    override fun addArticleIntoDB(articleEntity: ArticleEntity?): Observable<Long?> {
        articleDBMapper.
        return articleDbManager.addOrUpdate(articleEntity)
    }

}