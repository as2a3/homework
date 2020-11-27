package com.said.homework.news.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Ahmed Sa'eed on 27,November,2020
 */
@Entity(nameInDb = "source")
public class ArticleSourceDB {
    @Id(autoincrement = true)
    private Long id;

    private String cloudId;
    private String name;

    @Generated(hash = 1131533435)
    public ArticleSourceDB(Long id, String cloudId, String name) {
        this.id = id;
        this.cloudId = cloudId;
        this.name = name;
    }

    @Generated(hash = 218280658)
    public ArticleSourceDB() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCloudId() {
        return cloudId;
    }

    public void setCloudId(String cloudId) {
        this.cloudId = cloudId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
