package com.said.homework.news.data.model.mapper;

import com.said.homework.news.data.model.ArticleSourceDB;
import com.said.homework.news.domain.entity.ArticleSourceEntity;

/**
 * Created by Ahmed Sa'eed on 27,November,2020
 */
public class ArticleSourceDBMapper {
    public static ArticleSourceDB map(ArticleSourceEntity articleSourceEntity) {
        ArticleSourceDB articleSourceDB = new ArticleSourceDB();
        articleSourceDB.setId(articleSourceEntity.getLocalID());
        articleSourceDB.setCloudId(articleSourceEntity.getId());
        articleSourceDB.setName(articleSourceEntity.getName());
        return articleSourceDB;
    }
}
