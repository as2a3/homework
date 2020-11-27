package com.said.homework.news.data.db;

import android.util.Log;

import com.said.homework.base.data.exception.DatabaseException;
import com.said.homework.news.data.model.ArticleDB;
import com.said.homework.news.data.model.ArticleSourceDB;
import com.said.homework.news.data.model.mapper.ArticleDBMapper;
import com.said.homework.news.data.model.mapper.ArticleSourceDBMapper;
import com.said.homework.news.domain.entity.ArticleEntity;

import org.jetbrains.annotations.Nullable;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import database.ArticleDBDao;
import database.ArticleSourceDBDao;
import database.DaoSession;
import io.reactivex.Observable;

/**
 * Created by Ahmed Sa'eed on 27,November,2020
 */
@Singleton
public class ArticleDbManager implements ArticleDbCrud {
    private DaoSession mDaoSession;
    private ArticleDBDao articleDBDao;
    private ArticleSourceDBDao articleSourceDBDao;

    @Inject
    public ArticleDbManager() {
    }

    public DaoSession getDaoSession() throws DatabaseException {
        if (mDaoSession == null) {
            DatabaseException exception = new DatabaseException(new Throwable());
            exception.setMessage("You must call createDatabase(Context context, long userCloudId) first!");
            throw exception;
        }
        return mDaoSession;
    }

    @Nullable
    @Override
    public Observable<Boolean> initDAOs() {
        return Observable.create(emitter -> {
            try {
                mDaoSession = getDaoSession();
                articleDBDao = mDaoSession.getArticleDBDao();
                articleSourceDBDao = mDaoSession.getArticleSourceDBDao();
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
//                long articleSourceLocalID = articleSourceDBDao.queryBuilder()
//                        .where(ArticleSourceDBDao.Properties.CloudId.eq(articleEntity.getArticleSourceEntity().getId()))
//                        .unique()
//                        .getId();

                ArticleSourceDB articleSourceDB = ArticleSourceDBMapper.map(articleEntity.getArticleSourceEntity());
                articleSourceDBDao.insert(articleSourceDB);

                ArticleDB articleDB = ArticleDBMapper.map(articleEntity);
                articleDB.setArticleSourceID(articleSourceDB.getId());

                articleDBDao.insert(articleDB);

                Log.d("DaoExample", "Inserted new note, ID: " + articleDB.getId());
//                if (articleEntity.getLocalID() != -1) {
//                    SpaceDB existingSpaceDB = spaceDBDao.queryBuilder()
//                            .where(SpaceDBDao.Properties.CloudID.eq(spaceEntity.getCloudID()))
//                            .unique();
//
//                    if (existingSpaceDB != null)
//                        spaceDB.setId(existingSpaceDB.getId());
//                }
//
//                spaceDB.setCategoryID(spaceCategoryLocalID);
//                spaceDB.setCreatorID(creatorLocalID);
//                spaceDB.setOwnerID(ownerLocalID);
//
//                spaceDBDao.insertOrReplace(spaceDB);
//
//                insertTags(spaceDB.getId(), spaceEntity.getTags());
//                insertCommunities(spaceDB.getId(), spaceEntity.getCommunityList());
//
//                if (spaceEntity.getCloudID() != 0) {
//                    List<ContentDB> contentDBs = contentDBDao.queryBuilder().where(
//                            ContentDBDao.Properties.SpaceID.eq(spaceEntity.getCloudID())).list();
//                    for (ContentDB contentDB : contentDBs) {
//                        contentDB.setSpaceLocalID(spaceDB.getId());
//                        contentDB.update();
//                    }
//                }
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
        return null;
    }

    @Nullable
    @Override
    public Observable<ArticleEntity> getByLocalId(long localId) {
        return null;
    }

    @Nullable
    @Override
    public Observable<ArticleEntity> getByCloudId(long cloudId) {
        return null;
    }

    @Nullable
    @Override
    public Observable<List<ArticleEntity>> getAll() {
        return null;
    }
}
