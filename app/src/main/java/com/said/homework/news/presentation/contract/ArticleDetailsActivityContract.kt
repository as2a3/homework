package com.said.homework.news.presentation.contract

import com.said.homework.base.presentation.contract.BaseContract
import com.said.homework.base.presentation.view.activity.BaseActivity
import com.said.homework.news.domain.entity.ArticleEntity

/**
 * Created by Ahmed Sa'eed on 26/11/2020.
 */
interface ArticleDetailsActivityContract {
    interface View : BaseContract.View {
        fun onInitDatabaseSuccess()
        fun onInitDatabaseFailed(msg: String)
        fun onAddArticleToDBSuccess(localID: Long)
        fun onAddArticleToDBFailed(msg: String)
        fun onCheckIsFavorite(isFavorite: Boolean)
    }
    interface Presenter : BaseContract.Presenter<View?> {
        fun initDatabaseDao(baseActivity: BaseActivity)
        fun checkIsFavorite(baseActivity: BaseActivity, articleEntity: ArticleEntity)
        fun changeDBFavorite(baseActivity: BaseActivity, articleEntity: ArticleEntity)
    }
}