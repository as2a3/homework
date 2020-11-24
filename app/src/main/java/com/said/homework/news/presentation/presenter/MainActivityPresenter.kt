package com.said.homework.news.presentation.presenter

import android.util.Log
import com.said.homework.base.presentation.presenter.BasePresenter
import com.said.homework.news.domain.entity.GetNewsParamsEntity
import com.said.homework.news.domain.interactor.GetNewsUseCase
import com.said.homework.news.presentation.contract.MainActivityContract
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Ahmed Sa'eed on 23/11/2020.
 */
class MainActivityPresenter @Inject constructor(private val getNewsUseCase: GetNewsUseCase) : BasePresenter<MainActivityContract.View?>(),
    MainActivityContract.Presenter {

    override fun getNews() {
//        view?.showBlockingLoading()
        val getNewsParamsEntity = GetNewsParamsEntity(1, "bitcoin")
        addDisposable(getNewsUseCase
            .build(getNewsParamsEntity)
            ?.subscribeOn(Schedulers.io())
            ?.subscribe({ newsEntity ->
                run {
//                    Log.v("AhmedLog", "Total=" + newsEntity.total);
//                    Log.v("AhmedLog", "index=" + newsEntity.articleEntities.size);
//                    view?.hideBlockingLoading()
                }
            }
            ) { throwable ->
                throwable.printStackTrace()
//                Log.v("AhmedLog", "throwable=" + throwable.message)
            })
    }
}