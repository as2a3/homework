package com.said.homework.base.presentation.di.component

import android.app.Service
import com.said.homework.base.presentation.view.activity.BaseActivity
import com.said.homework.base.presentation.di.module.ApplicationModule
import com.said.homework.news.presentation.di.component.ArticleDetailsActivityComponent
import com.said.homework.news.presentation.di.component.MainActivityComponent
import com.said.homework.news.presentation.di.module.ArticleDetailsActivityModule
import com.said.homework.news.presentation.di.module.MainActivityModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(baseActivity: BaseActivity?)
    fun inject(syncService: Service?)
//    fun plus(mainActivityModule: MainActivityModule?): MainActivityComponent?

    operator fun plus(mainActivityModule: MainActivityModule?): MainActivityComponent?

    operator fun plus(articleDetailsActivityModule: ArticleDetailsActivityModule?): ArticleDetailsActivityComponent?
}