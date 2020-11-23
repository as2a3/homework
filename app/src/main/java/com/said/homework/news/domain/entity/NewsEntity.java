package com.said.homework.news.domain.entity;

import java.util.List;

/**
 * Created by Ahmed Sa'eed on 22/11/2020.
 */
public class NewsEntity {
    private String status;
    private Long total;
    private List<ArticleEntity> articleEntities;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<ArticleEntity> getArticleEntities() {
        return articleEntities;
    }

    public void setArticleEntities(List<ArticleEntity> articleEntities) {
        this.articleEntities = articleEntities;
    }
}
