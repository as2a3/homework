package com.said.homework.news.presentation.di.component

import com.said.homework.base.presentation.di.scope.PerFragment
import com.said.homework.news.presentation.di.module.HomeFragmentModule
import com.said.homework.news.presentation.view.fragment.HomeFragment
import dagger.Subcomponent

/**
 * Created by Ahmed Sa'eed on 24/11/2020.
 */
@PerFragment
@Subcomponent(modules = [HomeFragmentModule::class])
interface HomeFragmentComponent {
    fun inject(homeFragment: HomeFragment?)
}