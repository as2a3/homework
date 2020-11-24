package com.said.homework.news.presentation.contract

import com.said.homework.base.presentation.contract.BaseContract
import com.said.homework.news.domain.entity.NewsEntity
import com.said.homework.news.presentation.view.activity.MainActivity

/**
 * Created by Ahmed Sa'eed on 24/11/2020.
 */
interface HomeFragmentContract {
    interface View : BaseContract.View {
        fun onGetNewsSuccessful(newsEntity: NewsEntity)
        fun onGetNewsFailed(msg: String)
    }
    interface Presenter : BaseContract.Presenter<View?> {
        fun getNews(activity: MainActivity)
    }
}