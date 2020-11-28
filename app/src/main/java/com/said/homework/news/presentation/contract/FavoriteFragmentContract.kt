package com.said.homework.news.presentation.contract

import com.said.homework.base.presentation.contract.BaseContract
import com.said.homework.base.presentation.view.fragment.BaseFragment
import com.said.homework.news.domain.entity.ArticleEntity
import com.said.homework.news.domain.entity.NewsEntity

/**
 * Created by Ahmed Sa'eed on 28/11/2020.
 */
interface FavoriteFragmentContract {
    interface View : BaseContract.View {
        fun onGetFavoriteArticlesSuccessful(newsEntities: MutableCollection<ArticleEntity>)
        fun onGetFavoriteArticleFailed(msg: String)
    }
    interface Presenter : BaseContract.Presenter<View?> {
        fun initDaoSessions(fragment: BaseFragment)
        fun getFavoriteArticles(fragment: BaseFragment)
    }
}