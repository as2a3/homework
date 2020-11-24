package com.said.homework.news.presentation.di.module

import com.said.homework.base.presentation.di.scope.PerFragment
import com.said.homework.news.domain.interactor.GetNewsUseCase
import com.said.homework.news.presentation.presenter.HomeFragmentPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Ahmed Sa'eed on 23/11/2020.
 */
@Module
class HomeFragmentModule {

    @Provides
    @PerFragment
    fun providesHomeFragmentPresenter(getNewsUseCase: GetNewsUseCase?): HomeFragmentPresenter {
        return HomeFragmentPresenter(getNewsUseCase!!)
    }
}