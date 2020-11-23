package com.said.homework.news.data.network

import com.said.homework.AppConstants
import com.said.homework.base.data.network.BaseNetwork
import com.said.homework.base.data.network.RequestType
import com.said.homework.news.data.model.NewsCloud
import com.said.homework.news.domain.entity.GetNewsParamsEntity
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Ahmed Sa'eed on 22/11/2020.
 */
class NewsManagementCloud @Inject constructor() {
    @Inject
    lateinit var cloud: BaseNetwork
    fun getNewsArticles(getNewsParamsEntity: GetNewsParamsEntity): Observable<NewsCloud?>? {
        return cloud.create(NewsService::class.java, RequestType.DEFAULT_NO_HEADERS_REQUEST)
                .getNews(getNewsParamsEntity.page,
                        getNewsParamsEntity.keyword,
                        getNewsParamsEntity.from,
                        getNewsParamsEntity.sortBy,
                        AppConstants.API_KEY)
    }
}