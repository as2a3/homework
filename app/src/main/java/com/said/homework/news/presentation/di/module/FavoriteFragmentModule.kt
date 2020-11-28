package com.said.homework.news.presentation.di.module

import com.said.homework.base.presentation.di.scope.PerFragment
import com.said.homework.news.domain.interactor.GetDBArticlesUseCase
import com.said.homework.news.domain.interactor.InitDataBaseUseCase
import com.said.homework.news.presentation.presenter.FavoriteFragmentPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Ahmed Sa'eed on 23/11/2020.
 */
@Module
class FavoriteFragmentModule {

    @Provides
    @PerFragment
    fun providesFavoriteFragmentPresenter(initDataBaseUseCase: InitDataBaseUseCase?,
                                          getDBArticlesUseCase: GetDBArticlesUseCase?): FavoriteFragmentPresenter {
        return FavoriteFragmentPresenter(initDataBaseUseCase!!, getDBArticlesUseCase!!)
    }
}