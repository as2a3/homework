package com.said.homework.news.presentation.presenter

import com.said.homework.base.presentation.presenter.BasePresenter
import com.said.homework.news.presentation.contract.ArticleDetailsActivityContract
import javax.inject.Inject

/**
 * Created by Ahmed Sa'eed on 26/11/2020.
 */
class ArticleDetailsActivityPresenter @Inject constructor() : BasePresenter<ArticleDetailsActivityContract.View?>(),
    ArticleDetailsActivityContract.Presenter {
}