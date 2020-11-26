package com.said.homework.news.presentation.contract

import com.said.homework.base.presentation.contract.BaseContract
import com.said.homework.base.presentation.view.fragment.BaseFragment
import com.said.homework.news.domain.entity.GetNewsParamsEntity
import com.said.homework.news.domain.entity.NewsEntity

/**
 * Created by Ahmed Sa'eed on 24/11/2020.
 */
interface HomeFragmentContract {
    interface View : BaseContract.View {
        fun onGetAPIKey()
        fun onGetNewsSuccessful(newsEntity: NewsEntity)
        fun onGetNewsFailed(msg: String)
    }
    interface Presenter : BaseContract.Presenter<View?> {
        fun getRemoteAPIKey(fragment: BaseFragment)
        fun getNews(fragment: BaseFragment, params: GetNewsParamsEntity)
    }
}