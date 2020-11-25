package com.said.homework.news.presentation.presenter

import com.said.homework.base.data.exception.RetrofitException
import com.said.homework.base.data.model.ConnectionStateEnum
import com.said.homework.base.presentation.presenter.BasePresenter
import com.said.homework.base.presentation.view.fragment.BaseFragment
import com.said.homework.news.domain.entity.GetNewsParamsEntity
import com.said.homework.news.domain.interactor.GetNewsUseCase
import com.said.homework.news.presentation.contract.HomeFragmentContract
import com.said.homework.news.presentation.view.activity.MainActivity
import com.said.homework.news.presentation.view.fragment.HomeFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import java.lang.Exception
import javax.inject.Inject

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class HomeFragmentPresenter @Inject constructor(private val getNewsUseCase: GetNewsUseCase) : BasePresenter<HomeFragmentContract.View?>(),
        HomeFragmentContract.Presenter {

    override fun getNews(fragment: BaseFragment, params: GetNewsParamsEntity) {
        addDisposable(getNewsUseCase
                .build(params)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({ newsEntity ->
                    if (newsEntity.status.equals(ConnectionStateEnum.OK.status))
                        (fragment as HomeFragment).onGetNewsSuccessful(newsEntity)
                    else
                        (fragment as HomeFragment).onGetNewsFailed(newsEntity.message.toString())
                }
                ) { throwable ->
                    throwable.printStackTrace()
                    if (throwable is RetrofitException) {
                        try {
                            (fragment as HomeFragment).onGetNewsFailed(JSONObject(throwable.responseBody).get("message") as String)
                        } catch (e: Exception) {
                            (fragment as HomeFragment).onGetNewsFailed(throwable.message.toString())
                        }
                    } else {
                        (fragment as HomeFragment).onGetNewsFailed(throwable.message.toString())
                    }
                })
    }
}