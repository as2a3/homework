package com.said.homework.news.presentation.presenter

import com.said.homework.base.presentation.presenter.BasePresenter
import com.said.homework.base.presentation.view.fragment.BaseFragment
import com.said.homework.news.domain.interactor.GetDBArticlesUseCase
import com.said.homework.news.domain.interactor.InitDataBaseUseCase
import com.said.homework.news.presentation.contract.FavoriteFragmentContract
import com.said.homework.news.presentation.view.activity.ArticleDetailsActivity
import com.said.homework.news.presentation.view.fragment.FavoriteFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Ahmed Sa'eed on 28/11/2020.
 */
class FavoriteFragmentPresenter @Inject constructor(private val initDataBaseUseCase: InitDataBaseUseCase,
                                                    private val getDBArticlesUseCase: GetDBArticlesUseCase) :
    BasePresenter<FavoriteFragmentContract.View?>(), FavoriteFragmentContract.Presenter {
    override fun initDaoSessions(fragment: BaseFragment) {
        addDisposable(initDataBaseUseCase
            .build(null)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({
                getFavoriteArticles(fragment)
            }) {
                it.printStackTrace()
                it.printStackTrace()
                (fragment as FavoriteFragment).hideBlockingLoading()
                (fragment as FavoriteFragment).onGetFavoriteArticleFailed(it.message!!)
            })
    }


    override fun getFavoriteArticles(fragment: BaseFragment) {
        addDisposable(getDBArticlesUseCase
            .build(null)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe ({ it ->
                run {
                    (fragment as FavoriteFragment).hideBlockingLoading()
                    (fragment as FavoriteFragment).onGetFavoriteArticlesSuccessful(it)
                }
            }) {
                it.printStackTrace()
                (fragment as FavoriteFragment).hideBlockingLoading()
                (fragment as FavoriteFragment).onGetFavoriteArticleFailed(it.message!!)
            })
    }
}