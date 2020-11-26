package com.said.homework.news.presentation.di.component

import com.said.homework.base.presentation.di.scope.PerActivity
import com.said.homework.news.presentation.di.module.ArticleDetailsActivityModule
import com.said.homework.news.presentation.view.activity.ArticleDetailsActivity
import dagger.Subcomponent

/**
 * Created by Ahmed Sa'eed on 26/11/2020.
 */
@PerActivity
@Subcomponent(modules = [ArticleDetailsActivityModule::class])
interface ArticleDetailsActivityComponent {
    fun inject(articleDetailsActivity: ArticleDetailsActivity?)
}