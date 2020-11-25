package com.said.homework.news.presentation.di.module

import com.said.homework.base.presentation.di.scope.PerActivity
import com.said.homework.news.data.network.NewsManagementCloud
import com.said.homework.news.data.repository.NewsRepositoryImp
import com.said.homework.news.domain.interactor.GetNewsUseCase
import com.said.homework.news.domain.repository.NewsRepository
import com.said.homework.news.presentation.presenter.MainActivityPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Ahmed Sa'eed on 23/11/2020.
 */
@Module
class MainActivityModule {
    @Provides
    @PerActivity
    fun providesNewsRepository(newsManagementCloud: NewsManagementCloud?): NewsRepository {
        return NewsRepositoryImp(newsManagementCloud!!)
    }

    @Provides
    @PerActivity
    fun providesMainActivityPresenter(): MainActivityPresenter {
        return MainActivityPresenter()
    }
}