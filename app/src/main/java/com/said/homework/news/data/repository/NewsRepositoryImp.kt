package com.said.homework.news.data.repository

import com.said.homework.news.data.model.response.GetNewsResponse
import com.said.homework.news.data.network.NewsCloud
import com.said.homework.news.domain.repository.NewsRepository
import com.said.homework.news.domain.entity.GetNewsParamsEntity
import com.said.homework.news.domain.entity.NewsResponseEntity
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Ahmed Sa'eed on 22/11/2020.
 */
class NewsRepositoryImp @Inject constructor(private val newsCloud: NewsCloud) : NewsRepository {
    override fun getArticles(getNewsParamsEntity: GetNewsParamsEntity): Observable<GetNewsResponse?>? {
        return newsCloud.getNewsArticles(getNewsParamsEntity)
    }
}