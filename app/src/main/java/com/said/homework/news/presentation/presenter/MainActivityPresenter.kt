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
class MainActivityPresenter @Inject constructor(private val getNewsUseCase: GetNewsUseCase) : BasePresenter<MainActivityContract.View?>(),
    MainActivityContract.Presenter {

    override fun getNews(activity: MainActivity) {
        activity.showBlockingLoading()
        val getNewsParamsEntity = GetNewsParamsEntity(1, "bitcoin")
        addDisposable(getNewsUseCase
            .build(getNewsParamsEntity)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({ newsEntity ->
                activity.hideBlockingLoading()
                if (newsEntity.status.equals(ConnectionStateEnum.OK.status))
                    activity.onGetNewsSuccessful(newsEntity)
                else
                    activity.onGetNewsFailed(newsEntity.message.toString())
            }
            ) { throwable ->
                throwable.printStackTrace()
                activity.hideBlockingLoading()
                if (throwable is RetrofitException) {
                    try {
                        activity.onGetNewsFailed(JSONObject(throwable.responseBody).get("message") as String)
                    } catch (e: Exception) {
                        activity.onGetNewsFailed(throwable.message.toString())
                    }
                } else {
                    activity.onGetNewsFailed(throwable.message.toString())
                }
            })
    }
}