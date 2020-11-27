package com.said.homework.news.presentation.presenter

import com.said.homework.base.presentation.presenter.BasePresenter
import com.said.homework.base.presentation.view.activity.BaseActivity
import com.said.homework.news.domain.entity.ArticleEntity
import com.said.homework.news.domain.interactor.AddArticleToDBUseCase
import com.said.homework.news.domain.interactor.DeleteArticleToDBUseCase
import com.said.homework.news.domain.interactor.GetDBArticlesUseCase
import com.said.homework.news.domain.interactor.InitDataBaseUseCase
import com.said.homework.news.presentation.contract.ArticleDetailsActivityContract
import com.said.homework.news.presentation.view.activity.ArticleDetailsActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Ahmed Sa'eed on 26/11/2020.
 */
class ArticleDetailsActivityPresenter @Inject constructor(private val addArticleToDBUseCase: AddArticleToDBUseCase,
                                                          private val initDataBaseUseCase: InitDataBaseUseCase,
                                                          private val getDBArticlesUseCase: GetDBArticlesUseCase,
                                                          private val deleteArticleToDBUseCase: DeleteArticleToDBUseCase)
    : BasePresenter<ArticleDetailsActivityContract.View?>(), ArticleDetailsActivityContract.Presenter {

    override fun initDatabaseDao(baseActivity: BaseActivity) {
        (baseActivity as ArticleDetailsActivity).showBlockingLoading()
        addDisposable(initDataBaseUseCase
            .build(null)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({
                baseActivity.onInitDatabaseSuccess()
            }) {
                it.printStackTrace()
                baseActivity.run {
                    hideBlockingLoading()
                    onInitDatabaseFailed(it.message.toString())
                }
            })
    }

    override fun checkIsFavorite(baseActivity: BaseActivity, articleEntity: ArticleEntity) {
        addDisposable(getDBArticlesUseCase
            .build(null)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe ({ it ->
                run {
                    (baseActivity as ArticleDetailsActivity).hideBlockingLoading()
                    baseActivity.onCheckIsFavorite(isSavedBefore(articleEntity, it))
                }
            }) {
                it.printStackTrace()
                (baseActivity as ArticleDetailsActivity).hideBlockingLoading()
                baseActivity.onCheckIsFavorite(false)
            })
    }

    override fun changeDBFavorite(baseActivity: BaseActivity, articleEntity: ArticleEntity) {
        addDisposable(getDBArticlesUseCase
            .build(null)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe ({ it ->
                run {
                    if (isSavedBefore(articleEntity, it)) {
                        deleteFromDB(baseActivity, getArticleLocal(articleEntity, it))
                    } else {
                        addArticleToDB(baseActivity, articleEntity)
                    }
                }
            }) {
                it.printStackTrace()
                (baseActivity as ArticleDetailsActivity).hideBlockingLoading()
                baseActivity.onCheckIsFavorite(false)
            })
    }

    private fun addArticleToDB(baseActivity: BaseActivity, articleEntity: ArticleEntity) {
        addDisposable(addArticleToDBUseCase
            .build(articleEntity)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({ aLong ->
                (baseActivity as ArticleDetailsActivity).hideBlockingLoading()
                baseActivity.onCheckIsFavorite(true)
            }
            ) {
                it.printStackTrace()
                (baseActivity as ArticleDetailsActivity).hideBlockingLoading()
                baseActivity.onCheckIsFavorite(false)
            })
    }

    private fun deleteFromDB(baseActivity: BaseActivity, articleEntity: ArticleEntity) {
        addDisposable(deleteArticleToDBUseCase
            .build(articleEntity)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({ it ->
                (baseActivity as ArticleDetailsActivity).hideBlockingLoading()
                baseActivity.onCheckIsFavorite(true)
            }
            ) {
                it.printStackTrace()
                (baseActivity as ArticleDetailsActivity).hideBlockingLoading()
                baseActivity.onCheckIsFavorite(false)
            })
    }

    private fun isSavedBefore(articleEntity: ArticleEntity, entities: MutableList<ArticleEntity>): Boolean {
        for (entity: ArticleEntity in entities) {
            if (articleEntity.articleSourceEntity?.id == entity.articleSourceEntity?.id
                && articleEntity.articleSourceEntity?.name == entity.articleSourceEntity?.name
                && articleEntity.author == entity.author
                && articleEntity.title == entity.title
                && articleEntity.description == entity.description
                && articleEntity.url == entity.url
                && articleEntity.urlToImage == entity.urlToImage
                && articleEntity.content == entity.content
                && articleEntity.publishAt == entity.publishAt) {
                return true
            }
        }
        return false
    }

    private fun getArticleLocal(articleEntity: ArticleEntity, entities: MutableList<ArticleEntity>): ArticleEntity {
        for (entity: ArticleEntity in entities) {
            if (articleEntity.articleSourceEntity?.id == entity.articleSourceEntity?.id
                && articleEntity.articleSourceEntity?.name == entity.articleSourceEntity?.name
                && articleEntity.author == entity.author
                && articleEntity.title == entity.title
                && articleEntity.description == entity.description
                && articleEntity.url == entity.url
                && articleEntity.urlToImage == entity.urlToImage
                && articleEntity.content == entity.content
                && articleEntity.publishAt == entity.publishAt) {
                return entity
            }
        }
        return articleEntity
    }
}