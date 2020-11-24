package com.said.homework.news.presentation.di.component

import com.said.homework.base.presentation.di.scope.PerActivity
import com.said.homework.news.presentation.di.module.MainActivityModule
import com.said.homework.news.presentation.view.activity.MainActivity
import dagger.Subcomponent

/**
 * Created by Ahmed Sa'eed on 23/11/2020.
 */
@PerActivity
@Subcomponent(modules = [MainActivityModule::class])
interface MainActivityComponent {
    fun inject(mainActivity: MainActivity?)
}