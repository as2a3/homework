package com.said.homework.news.presentation.presenter

import com.said.homework.base.data.exception.RetrofitException
import com.said.homework.base.data.model.ConnectionStateEnum
import com.said.homework.base.presentation.presenter.BasePresenter
import com.said.homework.news.domain.entity.GetNewsParamsEntity
import com.said.homework.news.domain.interactor.GetNewsUseCase
import com.said.homework.news.presentation.contract.MainActivityContract
import com.said.homework.news.presentation.view.activity.MainActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import java.lang.Exception
import javax.inject.Inject

/**
 * Created by Ahmed Sa'eed on 23/11/2020.
 */
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MainActivityPresenter @Inject constructor() : BasePresenter<MainActivityContract.View?>(),
    MainActivityContract.Presenter {
}