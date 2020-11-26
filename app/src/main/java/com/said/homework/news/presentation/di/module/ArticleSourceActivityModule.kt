package com.said.homework.news.presentation.di.module

import com.said.homework.base.presentation.di.scope.PerActivity
import com.said.homework.news.presentation.presenter.ArticleSourceActivityPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Ahmed Sa'eed on 26/11/2020.
 */
@Module
class ArticleSourceActivityModule {

    @Provides
    @PerActivity
    fun providesArticleSourceActivityPresenter(): ArticleSourceActivityPresenter {
        return ArticleSourceActivityPresenter()
    }
}