package com.said.homework.news.data.network

import com.said.homework.base.data.network.BaseNetwork
import com.said.homework.base.data.network.RequestType
import com.said.homework.news.data.model.response.GetNewsResponse
import com.said.homework.news.domain.entity.GetNewsParamsEntity
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Ahmed Sa'eed on 22/11/2020.
 */
class NewsCloud @Inject constructor() {
    @Inject
    lateinit var cloud: BaseNetwork
    fun getNewsArticles(getNewsParamsEntity: GetNewsParamsEntity): Observable<GetNewsResponse?>? {
        return cloud.create(NewsService::class.java, RequestType.API_REQUEST)
                .getNews(getNewsParamsEntity.page,
                        getNewsParamsEntity.keyword,
                        getNewsParamsEntity.from,
                        getNewsParamsEntity.sortBy,
                        getNewsParamsEntity.apiKey)
    }
}