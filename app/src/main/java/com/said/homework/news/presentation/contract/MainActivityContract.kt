package com.said.homework.news.presentation.contract

import com.said.homework.base.presentation.contract.BaseContract
import com.said.homework.news.domain.entity.NewsEntity
import com.said.homework.news.presentation.view.activity.MainActivity

/**
 * Created by Ahmed Sa'eed on 23/11/2020.
 */
interface MainActivityContract {
    interface View : BaseContract.View
    interface Presenter : BaseContract.Presenter<View?>
}