package com.said.homework.news.presentation.presenter

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.remoteconfig.ktx.get
import com.said.homework.AppConstants.API_KEY
import com.said.homework.AppConstants.API_KEY_KEY
import com.said.homework.MyApp
import com.said.homework.R
import com.said.homework.base.data.exception.RetrofitException
import com.said.homework.base.data.model.ConnectionStateEnum
import com.said.homework.base.presentation.presenter.BasePresenter
import com.said.homework.base.presentation.view.fragment.BaseFragment
import com.said.homework.news.domain.entity.GetNewsParamsEntity
import com.said.homework.news.domain.interactor.GetNewsUseCase
import com.said.homework.news.presentation.contract.HomeFragmentContract
import com.said.homework.news.presentation.view.fragment.HomeFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import java.lang.Exception
import javax.inject.Inject

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class HomeFragmentPresenter @Inject constructor(private val getNewsUseCase: GetNewsUseCase) : BasePresenter<HomeFragmentContract.View?>(),
        HomeFragmentContract.Presenter {

    override fun getRemoteAPIKey(fragment: BaseFragment) {
        MyApp.remoteConfig?.fetchAndActivate()?.addOnCompleteListener(OnCompleteListener {task ->
            // Get API_KEY from Remote Config firebase
            API_KEY = MyApp.remoteConfig!![API_KEY_KEY].asString()
            (fragment as HomeFragment).onGetAPIKey()
        })
    }

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
                            delegateGetNewsException(fragment, throwable)
                        }
                    } else {
                        (fragment as HomeFragment).onGetNewsFailed(throwable.message.toString())
                    }
                })
    }

    private fun delegateGetNewsException(fragment: BaseFragment, exception: RetrofitException) {
        val msg: String
        if (exception.kind == RetrofitException.Kind.NETWORK) {
            msg = MyApp.get()?.resources?.getString(R.string.network_error)?: ""
        } else {
            msg = exception.message.toString()
        }
        (fragment as HomeFragment).onGetNewsFailed(msg)
    }
}