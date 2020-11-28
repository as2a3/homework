package com.said.homework.news.data.db;

import android.content.Context;
import android.util.Log;

import com.said.homework.MyApp;
import com.said.homework.base.data.exception.DatabaseException;
import com.said.homework.news.data.model.ArticleDB;
import com.said.homework.news.data.model.mapper.ArticleDBMapper;
import com.said.homework.news.domain.entity.ArticleEntity;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import database.ArticleDBDao;
import database.DaoSession;
import io.reactivex.Observable;

/**
 * Created by Ahmed Sa'eed on 27,November,2020
 */
@Singleton
public class ArticleDbManager implements ArticleDbCrud {
    private DaoSession mDaoSession;
    private ArticleDBDao articleDBDao;

    @Inject
    public ArticleDbManager() {
    }

    public void disconnectDatabase() {
        mDaoSession = null;
    }

    public DaoSession getDaoSession() throws DatabaseException {
        if (MyApp.Companion.getMDaoSession() == null) {
            DatabaseException exception = new DatabaseException(new Throwable());
            exception.setMessage("You must call createDatabase(Context context, long userCloudId) first!");
            throw exception;
        }
        return MyApp.Companion.getMDaoSession();
    }

    @Nullable
    @Override
    public Observable<Boolean> initDAOs() {
        return Observable.create(emitter -> {
            try {
                mDaoSession = getDaoSession();
                articleDBDao = mDaoSession.getArticleDBDao();
                emitter.onNext(true);
                emitter.onComplete();
            } catch (Exception e) {
                e.printStackTrace();
                emitter.onError(new DatabaseException(e));
            }
        });
    }

    @Nullable
    @Override
    public Observable<Long> addOrUpdate(ArticleEntity articleEntity) {
        return Observable.create(emitter -> {
            try {
                ArticleDB articleDB = ArticleDBMapper.map(articleEntity);
                articleDBDao.insert(articleDB);
                Log.d("DaoExample", "Inserted new note, ID: " + articleDB.getId());
                emitter.onNext(articleDB.getId());
                emitter.onComplete();
            } catch (Exception e) {
                e.printStackTrace();
                emitter.onError(new DatabaseException(e));
            }
        });
    }

    @Nullable
    @Override
    public Observable<Long> update(ArticleEntity articleEntity) {
        return null;
    }

    @Nullable
    @Override
    public Observable<Boolean> delete(ArticleEntity articleEntity) {
        articleDBDao.deleteByKey(articleEntity.getLocalID());
        return Observable.just(true);
    }

    @Nullable
    @Override
    public Observable<ArticleEntity> getByLocalId(long localId) {
        return Observable.just(ArticleDBMapper.map(articleDBDao.load(localId)));
    }

    @Nullable
    @Override
    public Observable<ArticleEntity> getByCloudId(long cloudId) {
        return null;
    }

    @Nullable
    @Override
    public Observable<List<ArticleEntity>> getAll() {
        ArrayList<ArticleDB> userDBs = (ArrayList<ArticleDB>) articleDBDao.loadAll();
        return Observable.just(ArticleDBMapper.mapToArticleEntities(userDBs));
    }
}
