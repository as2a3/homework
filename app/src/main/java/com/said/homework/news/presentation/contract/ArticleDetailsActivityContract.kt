package com.said.homework.news.presentation.contract

import com.said.homework.base.presentation.contract.BaseContract

/**
 * Created by Ahmed Sa'eed on 26/11/2020.
 */
interface ArticleDetailsActivityContract {
    interface View : BaseContract.View
    interface Presenter : BaseContract.Presenter<View?>
}