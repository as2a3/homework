package com.said.homework.news.presentation.presenter

import com.said.homework.base.presentation.presenter.BasePresenter
import com.said.homework.news.presentation.contract.ArticleSourceActivityContract
import javax.inject.Inject

/**
 * Created by Ahmed Sa'eed on 26/11/2020.
 */
class ArticleSourceActivityPresenter @Inject constructor() : BasePresenter<ArticleSourceActivityContract.View?>(),
        ArticleSourceActivityContract.Presenter {
}