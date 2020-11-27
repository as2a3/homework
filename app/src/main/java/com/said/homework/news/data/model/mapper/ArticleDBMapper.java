package com.said.homework.news.data.model.mapper;

import com.said.homework.news.data.model.ArticleDB;
import com.said.homework.news.domain.entity.ArticleEntity;

/**
 * Created by Ahmed Sa'eed on 27,November,2020
 */
public class ArticleDBMapper {
    public static ArticleDB map(ArticleEntity articleEntity) {
        ArticleDB articleDB = new ArticleDB();
        articleDB.setArticleSourceID(articleEntity.getArticleSourceEntity().getLocalID());
        articleDB.setAuthor(articleEntity.getAuthor());
        articleDB.setContent(articleEntity.getContent());
        articleDB.setDescription(articleEntity.getDescription());
        articleDB.setAuthor(articleEntity.getAuthor());
        articleDB.setPublishedAt(articleEntity.getPublishAt());
        articleDB.setTitle(articleEntity.getTitle());
        articleDB.setUrl(articleEntity.getUrl());
        articleDB.setUrlToImage(articleEntity.getUrlToImage());
        return articleDB;
    }
}
