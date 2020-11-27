package com.said.homework.news.presentation.presenter

import com.said.homework.base.presentation.presenter.BasePresenter
import com.said.homework.base.presentation.view.activity.BaseActivity
import com.said.homework.news.domain.entity.ArticleEntity
import com.said.homework.news.domain.interactor.AddArticleToDBUseCase
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
                                                          private val initDataBaseUseCase: InitDataBaseUseCase)
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

    override fun addArticleToDB(baseActivity: BaseActivity, articleEntity: ArticleEntity) {
        addDisposable(addArticleToDBUseCase
            .build(articleEntity)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({ aLong ->
                (baseActivity as ArticleDetailsActivity).onAddArticleToDBSuccess(aLong)
            }
            ) {
                it.printStackTrace()
                (baseActivity as ArticleDetailsActivity).onAddArticleToDBFailed(it.message.toString())
            })
    }
}