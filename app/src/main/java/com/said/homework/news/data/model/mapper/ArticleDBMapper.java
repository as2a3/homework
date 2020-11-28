package com.said.homework.news.data.model.mapper;

import com.said.homework.news.data.model.ArticleDB;
import com.said.homework.news.domain.entity.ArticleEntity;
import com.said.homework.news.domain.entity.ArticleSourceEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ahmed Sa'eed on 27,November,2020
 */
public class ArticleDBMapper {
    public static ArticleDB map(ArticleEntity articleEntity) {
        ArticleDB articleDB = new ArticleDB();
        if (articleEntity.getLocalID() >= 0)
            articleDB.setId(articleEntity.getLocalID());
        articleDB.setSourceId(articleEntity.getArticleSourceEntity().getId());
        articleDB.setSourceName(articleEntity.getArticleSourceEntity().getName());
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

    public static ArticleEntity map(ArticleDB articleDB) {
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setLocalID(articleDB.getId());
        articleEntity.setArticleSourceEntity(new ArticleSourceEntity(articleDB.getSourceId(), articleDB.getSourceName()));
        articleEntity.setAuthor(articleDB.getAuthor());
        articleEntity.setContent(articleDB.getContent());
        articleEntity.setDescription(articleDB.getDescription());
        articleEntity.setAuthor(articleDB.getAuthor());
        articleEntity.setPublishAt(articleDB.getPublishedAt());
        articleEntity.setTitle(articleDB.getTitle());
        articleEntity.setUrl(articleDB.getUrl());
        articleEntity.setUrlToImage(articleDB.getUrlToImage());
        return articleEntity;
    }

    public static List<ArticleEntity> mapToArticleEntities(List<ArticleDB> articleDBS) {
        List<ArticleEntity> articleEntities = new ArrayList<>();
        for (ArticleDB articleDB: articleDBS) {
            articleEntities.add(map(articleDB));
        }
        return articleEntities;
    }
}
