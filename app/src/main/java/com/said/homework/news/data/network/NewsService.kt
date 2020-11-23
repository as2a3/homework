package com.said.homework.news.data.network

import retrofit2.http.GET
import com.said.homework.news.data.model.NewsCloud
import io.reactivex.Observable
import retrofit2.http.Query
import java.util.*

/**
 * Created by Ahmed Sa'eed on 22/11/2020.
 */
interface NewsService {

    @GET("everything")
    fun getNews(@Query("page") page: Int,
                @Query("q") keyword: String?,
                @Query("from") from: Date?,
                @Query("sortBy") sortBy: String?,
                @Query("apiKey") apiKey: String?): Observable<NewsCloud?>?
}