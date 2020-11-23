package com.said.homework.news.domain.repository;

import com.said.homework.news.data.model.NewsCloud;
import com.said.homework.news.domain.entity.GetNewsParamsEntity;

import io.reactivex.Observable;

/**
 * Created by Ahmed Sa'eed on 22/11/2020.
 */
public interface NewsRepository {
    Observable<NewsCloud> getArticles(GetNewsParamsEntity getNewsParamsEntity);
}
