package com.said.homework.news.presentation.presenter

import com.said.homework.base.presentation.presenter.BasePresenter
import com.said.homework.news.domain.interactor.GetNewsUseCase
import com.said.homework.news.presentation.contract.HomeFragmentContract
import com.said.homework.news.presentation.view.activity.MainActivity
import javax.inject.Inject

class HomeFragmentPresenter @Inject constructor(private val getNewsUseCase: GetNewsUseCase) : BasePresenter<HomeFragmentContract.View?>(),
        HomeFragmentContract.Presenter {

    override fun getNews(activity: MainActivity) {
        TODO("Not yet implemented")
    }
}