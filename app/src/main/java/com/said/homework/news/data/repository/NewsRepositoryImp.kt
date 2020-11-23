package com.said.homework.news.data.repository

import com.said.homework.news.data.model.NewsCloud
import com.said.homework.news.data.network.NewsManagementCloud
import com.said.homework.news.domain.repository.NewsRepository
import com.said.homework.news.domain.entity.GetNewsParamsEntity
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Ahmed Sa'eed on 22/11/2020.
 */
class NewsRepositoryImp @Inject constructor(private val newsCloud: NewsManagementCloud) : NewsRepository {
    override fun getArticles(getNewsParamsEntity: GetNewsParamsEntity): Observable<NewsCloud?>? {
        return newsCloud.getNewsArticles(getNewsParamsEntity)
    }
}