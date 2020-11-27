package com.said.homework.news.data.repository

import com.said.homework.MyApp
import com.said.homework.news.data.db.ArticleDbManager
import com.said.homework.news.data.model.NewsCloud
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
                                            private val articleDbManager: ArticleDbManager) : NewsRepository {
    override fun initDatabaseDao(): Observable<Boolean?>? {
        articleDbManager.createDatabase(MyApp.get())
        return articleDbManager.initDAOs()
    }


    override fun getArticles(getNewsParamsEntity: GetNewsParamsEntity?): Observable<NewsCloud?>? {
        return getNewsParamsEntity?.let { newsCloud.getNews(it) }
    }

    override fun addArticleIntoDB(articleEntity: ArticleEntity?): Observable<Long?> {
        return articleDbManager.addOrUpdate(articleEntity)!!.map { it }
    }

}