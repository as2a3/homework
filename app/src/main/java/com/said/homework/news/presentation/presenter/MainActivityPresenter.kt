package com.said.homework.news.presentation.presenter

import com.said.homework.base.presentation.presenter.BasePresenter
import com.said.homework.news.presentation.contract.MainActivityContract
import javax.inject.Inject

/**
 * Created by Ahmed Sa'eed on 23/11/2020.
 */
class MainActivityPresenter @Inject constructor() : BasePresenter<MainActivityContract.View?>(),
    MainActivityContract.Presenter {
}