package com.said.homework.news.presentation.contract

import com.said.homework.base.presentation.contract.BaseContract
import com.said.homework.base.presentation.view.activity.BaseActivity
import com.said.homework.news.domain.entity.ArticleEntity

/**
 * Created by Ahmed Sa'eed on 26/11/2020.
 */
interface ArticleDetailsActivityContract {
    interface View : BaseContract.View {
        fun onAddArticleToDBSuccess(localID: Long)
        fun onAddArticleToDBFailed(msg: String)
    }
    interface Presenter : BaseContract.Presenter<View?> {
        fun addArticleToDB(baseActivity: BaseActivity, articleEntity: ArticleEntity)
    }
}